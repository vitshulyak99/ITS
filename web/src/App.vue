<template>
    <div id="app" class="container">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="#">ITS
<!--                {{new Date().getFullYear()}}-->
            </a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse col" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item ">
                        <router-link to="/home" class="nav-link"> Домашняя </router-link>
                    </li>
                    <li class="nav-item ">
                        <router-link to="/archive" class="nav-link"> Архив </router-link>
                    </li> <li class="nav-item ">
                        <router-link to="/committee" class="nav-link"> Комитеты </router-link>
                    </li>

                    <li class="nav-item " v-if="$store.getters.USER === null">
                        <router-link to="/login" class="nav-link"> Вход </router-link>
                    </li>
                    <li class="nav-item " >
                        <router-link to="/registration" class="nav-link" v-if="$store.getters.USER === null"> Регистрация</router-link>
                    </li>

                    <li class="nav-item" v-if="isUser">
                        <router-link to="/manageInfo" class="nav-link">Аккаунт</router-link>
                    </li>

                    <li class="nav-item "  v-if="isAdmin" >
                        <router-link to="/admin" class="nav-link" > Администратор </router-link>
                    </li>

                    <li class="nav-item " v-if="isConferee">
                        <router-link to="/conferee" class="nav-link" > Участник </router-link>
                    </li>

                    <li class="nav-item " v-if="isReviewer">
                        <router-link to="/reviewer" class="nav-link navbar-text">
                            <div v-if="isChair" class="d-inline-flex">
                                <span>Глава </span>
                                <span class="ml-1">секции</span>
                            </div>
                            <div v-else><span>
                                Рецензент
                            </span></div>
                        </router-link>
                    </li>

                    <li class="nav-item " v-if="isRedactor">
                        <router-link to="/redactor" class="nav-link"> Редактор </router-link>
                    </li>
                    <li class="col-5">

                    </li>

                    <li class="nav-item " v-if="$store.getters.USER !== null" >
                        <span class="navbar-text d-inline-flex" > Привет, <i class="text-center"> {{ /*$store.getters.FIO.split(' ')[0]*/"Ivan" }}</i>!</span>
                    </li>

                    <li class="nav-item " v-if="$store.getters.USER !== null" >
                        <Logout class="nav-link"/>
                    </li>
                </ul>
            </div>
        </nav>

        <br>
        <div class="container">
            <router-view ></router-view>
        </div>
        <!-- отображаем тут компонент, для которого совпадает маршрут -->
    </div>
</template>

<script>
    import AuthenticationService from './services/authenticate/authentication-service'
    import Logout from "./components/Logout";
    import AuthorizedService from "./services/authorized-service";
    import PublicDataService from "./services/publicData/public-data-service";

    export default {
        name: 'App',
        components: {Logout},
        methods:{
            logout(){
                AuthenticationService.logout();
            },
            ifTokeIsExist(){
                let token =  AuthenticationService.currentUser();

                console.log(token);

                if(token !== null && token!== undefined){
                    this.$store.commit('SET_TOKEN',token);

                    const authorizedService = new AuthorizedService(token);
                    console.log('try response');
                    authorizedService.createGet(require('./services/authenticate/authenticate-endpoints').default.IfExistToken)
                        .then(response => {
                            console.log(response);
                            const data = response.data;
                            const id = data.id;
                            const email = data.email;
                            const roles = data.roles;
                            const sections = data.sections;
                            const fio = data.fio;
                            this.$store.commit('SET_ID', id);
                            this.$store.commit('SET_EMAIL',email);
                            this.$store.commit('SET_ROLES',roles);
                            this.$store.commit('SET_SECTIONS',sections);
                            this.$store.commit('SET_FIO',fio);

                        })
                        .catch(e=>{
                            console.log(e);
                            AuthenticationService.logout();
                        });
                }
            },
            loadData(){
                PublicDataService.getDegrees().then(data=>{
                    console.log(data);
                    this.$store.commit('SET_ALL_DEGREES',data)
                });

                PublicDataService.getRoles().then(data=>{
                    console.log(data);
                    this.$store.commit('SET_ALL_ROLES',data)
                });

                PublicDataService.getSections().then(data=>{
                    console.log(data);
                    this.$store.commit('SET_ALL_SECTIONS',data)
                });
            }
        },
        created() {
           this.ifTokeIsExist();
           this.loadData()
        },
        computed: {
            isAdmin() {
                return  this.$store.getters.ROLES.some(r => r.name === 'ADMIN')
            },
            isUser() {
                return  this.$store.getters.ROLES.some(r => r.name === 'USER')
            },
            isConferee() {
                return  this.$store.getters.ROLES.some(r => r.name === 'CONFEREE')
            },
            isReviewer() {
                return this.$store.getters.ROLES.some(r => r.name === 'REVIEWER')
            },
            isChair() {
                return  this.$store.getters.ROLES.some(r => r.name === 'CHAIR')
            },
            isRedactor() {
                return this.$store.getters.ROLES.some(r => r.name === 'REDACTOR');
            }
        }
    }
</script>

<style>
    .border-radius-10{
        border-radius: 10px;
    }
    .border-radius-5{
        border-radius: 5px;
    }
    .border-radius-15{
        border-radius: 15px;
    }
</style>
