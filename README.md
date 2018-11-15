# 使用说明

##### Android Debug Database
###### 完全改变了debug 数据库和shared preferences的方式。现在你可以在一个漂亮的界面上查看，编辑，删除数据，以及运行sql语句

1.debugImplementation 'com.amitshekhar.android:debug-db:1.0.4'

2.连接方式

    (1)usb连接电脑

        命令行运行 adb forward tcp:8080 tcp:8080

    (2)手机和电脑连接Wifi

3.在浏览器里输入网址

    http://localhost:8080/