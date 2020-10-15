import Axios from "axios";

export default class AuthorizedService {

    // axios = null;
    token = null;

    constructor(token) {
        this.token= `Bearer_${token}`;
        // this.axios = Axios.create();
        // this.axios.interceptors.request.use(
        //     function (config) {
        //     config.headers.Authorization = this.token;
        //     return config;
        // }, function (error) {
        //     // Do something with request error
        //     return Promise.reject(error);
        // });
    }

    createPost(url,body){
        return  Axios.post(
            url,
            body,
            {
                headers: {
                    Authorization: this.token
                }
            })
    }
    createGet(url,params=null){

        if(params){
            return Axios.get(
                url,
                {
                    headers: {
                        Authorization: this.token
                    },
                    params
                })
        }else {
            return Axios.get(
                url,
                {
                    headers: {
                        Authorization: this.token
                    }
                })
        }


    }

}