const base = {
    get() {
        return {
            url : "http://localhost:8080/springbootdti6c7gx/",
            name: "springbootdti6c7gx",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/springbootdti6c7gx/front/dist/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "千锋智慧运动AI应用平台"
        } 
    }
}
export default base
