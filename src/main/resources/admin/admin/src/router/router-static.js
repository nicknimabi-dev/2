import Vue from 'vue';
//配置路由
import VueRouter from 'vue-router'
Vue.use(VueRouter);
//1.创建组件
import Index from '@/views/index'
import Home from '@/views/home'
import Login from '@/views/login'
import NotFound from '@/views/404'
import UpdatePassword from '@/views/update-password'
import pay from '@/views/pay'
import register from '@/views/register'
import center from '@/views/center'
	import news from '@/views/modules/news/list'
	import discussyundongzhishi from '@/views/modules/discussyundongzhishi/list'
	import zhishileibie from '@/views/modules/zhishileibie/list'
	import sensitivewords from '@/views/modules/sensitivewords/list'
	import syslog from '@/views/modules/syslog/list'
	import jianbuhuodong from '@/views/modules/jianbuhuodong/list'
	import jiankangbaogao from '@/views/modules/jiankangbaogao/list'
	import forum from '@/views/modules/forum/list'
	import forumtype from '@/views/modules/forumtype/list'
	import yundongzhishi from '@/views/modules/yundongzhishi/list'
	import yundongjilu from '@/views/modules/yundongjilu/list'
	import yonghu from '@/views/modules/yonghu/list'
	import huodongbaoming from '@/views/modules/huodongbaoming/list'
	import chat from '@/views/modules/chat/list'
	import forumreport from '@/views/modules/forumreport/list'
	import discussjianbuhuodong from '@/views/modules/discussjianbuhuodong/list'
	import config from '@/views/modules/config/list'
	import huodongleixing from '@/views/modules/huodongleixing/list'
	import yundongjihua from '@/views/modules/yundongjihua/list'
	import newstype from '@/views/modules/newstype/list'


//2.配置路由   注意：名字
export const routes = [{
	path: '/',
	name: '系统首页',
	component: Index,
	children: [{
		// 这里不设置值，是把main作为默认页面
		path: '/',
		name: '系统首页',
		component: Home,
		meta: {icon:'', title:'center', affix: true}
	}, {
		path: '/updatePassword',
		name: '修改密码',
		component: UpdatePassword,
		meta: {icon:'', title:'updatePassword'}
	}, {
		path: '/pay',
		name: '支付',
		component: pay,
		meta: {icon:'', title:'pay'}
	}, {
		path: '/center',
		name: '个人信息',
		component: center,
		meta: {icon:'', title:'center'}
	}
	,{
		path: '/news',
		name: '运动资讯',
		component: news
	}
	,{
		path: '/discussyundongzhishi',
		name: '运动知识评论',
		component: discussyundongzhishi
	}
	,{
		path: '/zhishileibie',
		name: '知识类别',
		component: zhishileibie
	}
	,{
		path: '/sensitivewords',
		name: '敏感词',
		component: sensitivewords
	}
	,{
		path: '/syslog',
		name: '系统日志',
		component: syslog
	}
	,{
		path: '/jianbuhuodong',
		name: '健步活动',
		component: jianbuhuodong
	}
	,{
		path: '/jiankangbaogao',
		name: '健康报告',
		component: jiankangbaogao
	}
	,{
		path: '/forum',
		name: '社交互动',
		component: forum
	}
	,{
		path: '/forumtype',
		name: '论坛分类',
		component: forumtype
	}
	,{
		path: '/yundongzhishi',
		name: '运动知识',
		component: yundongzhishi
	}
	,{
		path: '/yundongjilu',
		name: '运动记录',
		component: yundongjilu
	}
	,{
		path: '/yonghu',
		name: '用户',
		component: yonghu
	}
	,{
		path: '/huodongbaoming',
		name: '活动报名',
		component: huodongbaoming
	}
	,{
		path: '/chat',
		name: '健康咨询',
		component: chat
	}
	,{
		path: '/forumreport',
		name: '举报记录',
		component: forumreport
	}
	,{
		path: '/discussjianbuhuodong',
		name: '健步活动评论',
		component: discussjianbuhuodong
	}
	,{
		path: '/config',
		name: '轮播图管理',
		component: config
	}
	,{
		path: '/huodongleixing',
		name: '活动类型',
		component: huodongleixing
	}
	,{
		path: '/yundongjihua',
		name: '运动计划',
		component: yundongjihua
	}
	,{
		path: '/newstype',
		name: '运动资讯分类',
		component: newstype
	}
	]
	},
	{
		path: '/login',
		name: 'login',
		component: Login,
		meta: {icon:'', title:'login'}
	},
	{
		path: '/register',
		name: 'register',
		component: register,
		meta: {icon:'', title:'register'}
	},
	{
		path: '*',
		component: NotFound
	}
]
//3.实例化VueRouter  注意：名字
const router = new VueRouter({
	mode: 'hash',
	/*hash模式改为history*/
	routes // （缩写）相当于 routes: routes
})
const originalPush = VueRouter.prototype.push
//修改原型对象中的push方法
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => err)
}
export default router;
