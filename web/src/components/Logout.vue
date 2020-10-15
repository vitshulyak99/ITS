<template>
    <button class="btn" @click="logout"> Выход</button>
</template>

<script>
    import AuthenticationService from "../services/authenticate/authentication-service";

    export default {
        name: "Logout",
        methods: {
            logout() {
                AuthenticationService.logout();
                this.$store.commit('RESET_STATE');
                this.$router.push('home');
            }
        }
    }
</script>

<style scoped>

</style>