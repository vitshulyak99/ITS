import AccountEndpoints from "./account-endpoints";
import AuthorizedService from './../authorized-service';

export default class AccountService extends AuthorizedService{

    constructor(token) {
        super(token);
        console.log(this.token);
    }

    changePassword(email,oldPass,newPass,confirmPass){
        if(newPass!==confirmPass){
            throw Error('new password and confirmation not match!');
        }

        return this.createPost(AccountEndpoints.ChangePassword,{
            email,
            oldPassword: oldPass,
            newPassword: newPass,
            confirmPassword:confirmPass
        }).then(response => response.data)
            .catch(e=> console.log('change password\n' + e));
    }

    getName(email){
        return this.createPost(AccountEndpoints.GetName, {email} )
            .then(response =>{
                if(response) {

                    return {
                        name: response.data.name,
                        surname: response.data.surname,
                        patronymic: response.data.patronymic,
                    }
                }
            })
            .catch(e=>console.log( 'get name\n' +e));
    }

    updateName(email,name,surname,patronymic){
        return this.createPost(AccountEndpoints.ChangeName,
            {
                email,
                name,
                surname,
                patronymic
            }).then(response => response.data)
            .catch(e=>console.log('update name\n' + e));
    }


     updateInfo(organization,address, phone, country, degree){
        return this.createPost(AccountEndpoints.ChangeInfo,{
            organization,
            address,
            phone,
            country,
            degree
        })
            .then(response=>response.data)
            .catch(e=> console.log(e));
     }
}