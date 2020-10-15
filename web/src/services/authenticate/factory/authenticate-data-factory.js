// request data factory for authentication service

class AuthenticateDataFactory{
    createLoginData(email, password){
        return {
            email: email,
            password: password
        }
    }

    createResponseLoginData(data){
        if(typeof (data) === 'object') {
            return {
                id: data.id,
                email: data.email,
                token: data.token,
                roles: data.roles,
                sections:data.sections
            }
        }
        return null;
    }

    createRegistrationData(email, password, confirmPassword){
        return {
            email: email,
            password: password,
            confirmPassword: confirmPassword
        }
    }

    createRegistrationResponseData(data){
        if(typeof (data) === 'object'){
            return {
                id: data.id,
                type: data.type,
                email: data.email
            }
        }
        return null;
    }


}

export default new AuthenticateDataFactory();
