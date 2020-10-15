
const OK = 0;
const CREDENTIALS_ERROR = 1;
const USER_IS_EXIST_ERROR = 2;
const PASSWORD_CONFIRMATION_ERROR = 3;

class ResponseType {
    static get OK() { return OK }
    static get CREDENTIALS_ERROR() { return CREDENTIALS_ERROR }
    static get USER_IS_EXIST_ERROR() { return USER_IS_EXIST_ERROR }
    static get PASSWORD_CONFIRMATION_ERROR(){ return PASSWORD_CONFIRMATION_ERROR }
}

export default new ResponseType();