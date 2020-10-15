
const server = 'http://localhost:9000';

export default class AccountEndpoints {
    static get ChangePassword() {
        return `${server}/user/changePassword`;
    }
    static get GetName() {
        return `${server}/user/getName`;
    }
    static get ChangeName() {
        return `${server}/user/changeName`;
    }

    static get ChangeInfo() {
        return `${server}/user/changeInfo`;
    }



}