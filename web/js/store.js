window.$store = {
    set userInfo(userInfo){
        window.localStorage.setItem('userInfo', JSON.stringify(userInfo))
    },
    get userInfo(){
        return JSON.parse(window.localStorage.getItem('userInfo'))
    },
    set token(token){
        window.localStorage.setItem('token', token)
    },
    get token(){
        return window.localStorage.getItem('token')
    }
}