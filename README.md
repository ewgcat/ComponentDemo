#yijian
点击下面跳转到单独模块，这界面仅仅是一个空壳app工程,
下面的模块可以单独调试，单独运行，解耦，采用目前主流的开发框架:
 Rxjava2+retrofit+mvp+dagger2+Rxbus,

 ###没有用butterKnife原因
    由于module单独调试时,需要切换library和application状态，
    而当module变成library的时候，资源id是不能为常量的,
    导致用butterKnife出现各种错误，
    所以没有用butterKnife
