#!/usr/bin/env bash
#编译+部署

#需要配置如下参数
#项目路径，在Execute Shell中配置项目路径，pwd就可以获得该项目路径
#export PROJ_PATH=这个jenkins任务在部署机器上的路径

### base 函数
killTomcat()
{
	pid=`ps -ef|grep tomcat|grep java|awk '{print $2}'`
	echo "tomcat Id list :$pid"
	if [ "pid" = ""]
	then
		echo "no tomcat pid alive"
	else 
		kill -9 $pid
	fi
}

killPort()
{
	port=8081
	#根据端口号查询对应的pid
	pid=$(netstat -nlp | grep :$port | awk '{print $7}' | awk -F"/" '{ print $1 }');

	#杀掉对应的进程，如果pid不存在，则不执行
	if [  -n  "$pid"  ];  then
		kill  -9  $pid;
	fi
}

cd $PROJ_PATH/BaseLineSvr
echo "$PROJ_PATH"
mvn clean install

#停止tomcat
killPort

#删除原有工程
#rm -rf $TOMCAT_APP_PATH/webapps/ROOT
#rm -rf $TOMCAT_APP_PATH/webapps/ROOT.war
#rm -rf $TOMCAT_APP_PATH/webapps/roder.war
rm -rf $WORK_PATH/BaseLine.jar


#复制新的工程
#cp $PROJ_PATH/order/target/order.war $TOMCAT_APP_PATH/webapps/
cp $PROJ_PATH/BaseLineSvr/target/BaseLine-0.0.1-SNAPSHOT.jar $WORK_PATH/

cd $WORK_PATH/
#mv order.war ROOT.war
mv BaseLine-0.0.1-SNAPSHOT.jar BaseLine.jar

#启动tomcat
cd $WORK_PATH/
#sh bin/startup.sh
java -jar BaseLine.jar --server.port=8081 log.out 2>&1 &