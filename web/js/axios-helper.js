axios.defaults.headers['x-skk-token'] = $store.token
axios.defaults.baseUrl = $server
axios.defaults.transforResponse = function (data){
    data = JSON.parse(data)
    if(data.reason.indexOf('失效') != -1){
        $store.token = ''
        $store.userInfo = {}
        window.location.href = "index.html"
    }
    return data
}