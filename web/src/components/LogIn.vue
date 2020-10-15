<template>
    <div class="col-4">

        <form @submit.prevent="login">
            <div class="form-group">
                <label for="exampleEmail">Электронная почта</label>
                <input type="text"
                       class="form-control"
                       v-model.trim="$v.email.$model"
                       id="exampleEmail"
                       aria-describedby="usernameHelp"
                       placeholder="example@ex.c">
                <small class="form__error" v-if="$v.email.$dirty && !$v.email.required">
                    *Это поле обязательно к заполнению
                </small>

            </div>
            <div class="form-group">
                <label for="exampleInputPassword1">Пароль</label>
                <input type="password"
                       class="form-control"
                       id="exampleInputPassword1"
                       v-model.trim="$v.password.$model"
                       placeholder="Введите пароль">
                <small class="form__error" v-if="$v.password.$dirty && !$v.password.required">
                    *Это поле обязательно к заполнению
                </small>
                <small class="form__error" v-if="$v.password.$dirty && !$v.password.maxLength">
                    *Максимальная длина {{$v.password.$params.maxLength.max}} символов
                    <br>
                </small>
                <small class="form__error"  v-if="$v.password.$dirty &&  !$v.password.minLength">
                    *Минимальная длина {{$v.password.$params.minLength.min}} символов
                    <br>
                </small>
            </div>
            <div class="text-left" v-if="message!==''">
                <small class="form__error">
                    *{{message}}
                    <br>
                </small>
            </div>
            <button type="submit" class="btn btn-primary" :disabled="$v.$invalid">Войти</button>
            <router-link class="btn btn-link" to="/">Отмена</router-link>
            <button href="#/stublinkabcd" class="btn btn-link">Забыли пароль?</button>

        </form>
    </div>
</template>

<script>
    import AuthenticationService  from '../services/authenticate/authentication-service';
    import {required,email,maxLength,minLength} from "vuelidate/lib/validators";
    import {submitState} from "../services/submit-state";
    import ThrowableExceptions from "../services/authenticate/exceptions/throwable-exceptions";



    export default {
        name: "LogIn",
        data() {
            return {
                email: '',
                password: '',
                message:'',
                submitStatus: submitState.NOT_SUBMIT,
            }
        },
        validations: {
            email: {required, email},
            password: {
                required,
                minLength: minLength(6),
                maxLength: maxLength(20)
            }
        },
        methods: {
            login() {

                if (this.$v.$invalid) {
                    this.$v.$touch();
                }
                else {
                    console.log(this.email+' \n'+ this.password)
                    AuthenticationService.login(this.email, this.password)
                        .then(responseData => {
                        if (responseData.token !== null) {
                            this.$store.commit('SET_ID', responseData.id);
                            this.$store.commit('SET_EMAIL', responseData.email);
                            console.log(responseData.token);
                            this.$store.commit('SET_TOKEN', responseData.token);
                            this.$store.commit('SET_ROLES', responseData.roles);
                            this.$store.commit('SET_SECTIONS',responseData.sections);
                            this.$store.commit('SET_FIO',responseData.fio);
                            console.log(this.$store.getters.USER);
                            this.$router.push({path: 'conferee'});
                        }
                    }).catch(e=>{

                        let message = ThrowableExceptions.getMessage(e);
                        console.log(message);
                        this.message = message;

                    });


                }
            }
        }
    }
</script>

<style scoped>
    .form__error{
        color: red;
    }

</style>