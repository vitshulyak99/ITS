const server = 'http://localhost:9000';

export default {
    GET: {
        getUsers: `${server}/admin/getUsers`,
    },
    POST: {
        changeRoles: `${server}/admin/changeRole`,
        CreateUser: `${server}/admin/createUser`
    }
}