import AuthorizedService from "../authorized-service";

const endpoints = require('./admin-endpoints').default;

export default class AdminService extends AuthorizedService{

    getUserList(){

       return this.createGet(endpoints.GET.getUsers).then(response=> {
           const rdata = response.data;

           console.log(rdata);

           return rdata;
       })
           .catch(e=>{
                console.log(e);
                throw e;
           })

    }

    changeRoles(id,roles){
       return this.createPost(endpoints.POST.changeRoles,{id,roles}).then(response=>{
            response.data
        }).catch(e=> console.log(e));
    }

    createUser(email,roles,sections){

       return this.createPost(endpoints.POST.CreateUser,{
            email,
            roles,
            sections
        }).then(response=>response.data);
    }
}