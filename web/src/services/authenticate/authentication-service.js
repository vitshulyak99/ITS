const Axios = require('axios').default;
import Endpoints from './authenticate-endpoints';
import AuthenticateDataFactory from './factory/authenticate-data-factory';
import Cookies from 'js-cookie';

class AuthenticationService {

    constructor() {


        console.log(Endpoints);
    }

    login(email, password) {
        let loginData = AuthenticateDataFactory.createLoginData(email,password);

        return Axios.post(Endpoints.login,loginData)
            .then(response => {

                const data = response.data;
                console.log(data);
                Cookies.set('its_usr_cred',data.token, {expires: 365});
                console.log(Cookies.get('its_usr_cred')===data.token);
                return data;
            }).catch(e=> {
                console.log( e);
                this.logout();
                throw new Error(e.response.data.exception);
            });
    }

    registration(formData) {

        return  Axios.post(Endpoints.registration, formData)
            .then(response => {

                return AuthenticateDataFactory.createRegistrationResponseData( response.data);
            })
            .catch(e => {
                console.log(e);
                throw new Error(e.response.data.exception);
            });

    }

    logout() {
        Cookies.remove('its_usr_cred');
    }

    currentUser() {

        try{
            let token =  Cookies.get('its_usr_cred');

            return token;
        }catch (e) {
            console.log(e);
        }
    }


    getDataForForm(){
        console.log();
        return Axios.get(Endpoints.getSections)
            .then(response =>{

                console.log(response);
                return { degree:response.data.degree,
                sections : response.data.sections};
            })
            .catch(e=> {
                throw e;
            });
    }

    getIfTokenExist(token){

        const authorization = 'Bearer_'+token;
        this.logout(authorization);

        return Axios
            .get(Endpoints.IfExistToken,
            {
                headers:{
                    Authorization: authorization
                }
            })
            .then(response=>{
                console.log(response);
                return response.data;
            }).catch(e=>{
                console.log(e)
            });
    }
}

export default new AuthenticationService();