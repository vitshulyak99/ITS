<template>
    <div >
        <div>

            <!--            email -->
            <div class="form-group" >
                <label for="exampleInputEmail1">Адрес электронной почты</label>
                <input type="email"
                       class="form-control"
                       v-model.trim="$v.form.email.$model"
                       id="exampleInputEmail1"
                       aria-describedby="emailHelp"
                       placeholder="Enter email"
                >
                <small class="form__error" v-if="$v.form.email.$dirty && !$v.form.email.email">
                    *Введите адресс электронной почты
                </small>
                <small class="form__error" v-if="$v.form.email.$dirty && !$v.form.email.required">
                    *Это поле обязательно к заполнению
                </small>

            </div>

            <!--            sections-->
            <div class="form-group d-flex flex-column" >
                <label for="sections">Роли</label>
                <small>* Для выборанескольких зажмите Ctrl</small>
                <select name="sections"
                        id="sections"
                        class="from-control border-radius"
                        v-model="form.roles"
                        multiple>
                    <option v-for="role in allRoles"
                            :value="role"
                            :key="role.id">{{role.name}}</option>
                </select>
            </div>

            <!--            roles-->
            <div class="form-group d-flex flex-column" >
                <label for="roles">Секции</label>
                <small>* Для выборанескольких зажмите Ctrl</small>
                <select name="sections"
                        id="roles"
                        class="from-control border-radius"
                        v-model="form.section"
                        multiple>
                    <option v-for="section in allSections"
                            :value="section"
                            :key="section.id">{{section.name}}</option>
                </select>

            </div>

<!--            <div class="text-left" v-if="message!==''">-->
<!--                <small class="form__error">-->
<!--                    *{{message}}-->
<!--                    <br>-->
<!--                </small>-->
<!--            </div>-->

            <br>
            <button type="submit" class="btn btn-primary"  :disabled="$v.$invalid" @click="create" > Создать </button>

        </div>
    </div>
</template>

<script>
    import {required, email} from 'vuelidate/src/validators'
    import AdminService from "../../services/admin/admin-service";

    export default {
        name: "CreateUser",
        data(){
            return {
                form:{
                    section:[],
                    email:'',
                    roles:[]
                },
                sections:[],
                roles:[]
            }
        },
        validations:{
            form:{
                email: {required,email}
            }
        },
        computed:{
            allRoles(){
                const allRoles = this.$store.getters.ALL_ROLES;
                console.log('roles');
                console.log(allRoles);

                return allRoles;
            },
            allSections(){
                const allSections = this.$store.getters.ALL_SECTIONS;
                console.log('sections');
                console.log(allSections);

                return allSections;
            },
        },
        mounted() {

        },
        methods:{
            create(){
                let service = new AdminService(this.$store.getters.TOKEN);
                service.createUser(this.form.email,this.form.roles,this.form.sections).then(data=>alert(data));
            }
        }
    }
</script>

<style scoped>
    .border-radius{
        border-radius: 10px;
    }
</style>