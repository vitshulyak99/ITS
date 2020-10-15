<template>
    <div >
        <div class="">
            <!--            name-->
            <div class="d-inline-flex">

                <div class="form-group col-4 ">
                    <label for="name">Имя</label>
                    <input type="text"
                           class="form-control"
                           id="name"
                           v-model="$v.form.name.$model">
                </div>

                <!--            surname-->
                <div class="form-group col-4">
                    <label for="surname" > Фамилия</label>
                    <input type="text"
                           class="form-control"
                           id="surname"
                           v-model="$v.form.surname.$model">
                </div>
                <!--            patronymic-->
                <div class="form-group col-4">
                    <label for="patronymic" > Отчество </label>
                    <input type="text"
                           id="patronymic"
                           class="form-control"
                           v-model="$v.form.patronymic.$model">
                </div>
            </div>

            <div class="d-inline-flex">
                <!--            email -->
                <div class="form-group col-4 " >
                    <label for="exampleInputEmail1">Адрес электронной почты</label>
                    <input type="email"
                           class="form-control"
                           v-model.trim="$v.form.email.$model"
                           id="exampleInputEmail1"
                           aria-describedby="emailHelp"
                           placeholder="Enter email"
                    >
                    <small class="form__error" v-if="$v.form.email.$dirty && !$v.form.email.email">
                        *Enter email
                    </small>
                    <small class="form__error" v-if="$v.form.email.$dirty && !$v.form.email.required">
                        *This field is required
                    </small>
                </div>


                <!--          password-->
                <div class="form-group col-4">
                    <label for="exampleInputPassword1">Пароль</label>
                    <input type="password"
                           class="form-control"
                           id="exampleInputPassword1"
                           v-model.trim="$v.form.password.$model"
                           placeholder="Введите пароль">
                    <small class="form__error" v-if="$v.form.password.$dirty && !$v.form.password.required">
                        *Это поле является обязательным
                        <br>
                    </small>
                    <small class="form__error" v-if="$v.form.password.$dirty && !$v.form.password.isPasswordRightSymbols">
                        Пароль должен содержать символы от 'a' до 'z' с учетом регистра, цифры 0-9 и символы '!','?, '_'
                        <br>
                    </small>
                    <small class="form__error" v-if="$v.form.password.$dirty && !$v.form.password.maxLength">
                        *Максимальное количество символов {{$v.password.$params.maxLength.max}}
                        <br>
                    </small>
                    <small class="form__error"  v-if="$v.form.password.$dirty &&  !$v.form.password.minLength">
                        *Минимальное количество символов {{$v.password.$params.minLength.min}}
                        <br>
                    </small>
                </div>
                <!--          confirm password-->
                <div class="form-group col-4" >
                    <label for="exampleInputConfirmPassword1">Подтверждение пароля</label>
                    <input type="password"
                           class="form-control"
                           id="exampleInputConfirmPassword1"
                           v-model.trim = "$v.form.confirmPassword.$model"
                           placeholder="Повторите пароль">
                    <small class="form__error" v-if="$v.form.confirmPassword.$dirty && !$v.form.confirmPassword.required">
                        *Это поле является обязательным
                        <br>
                    </small>
                    <small class="form__error"  v-if=" $v.form.confirmPassword.$dirty &&  !$v.form.confirmPassword.sameAsPassword">
                        *Пароли не совпадают
                        <br>
                    </small>
                </div>

            </div>
        </div>

        <div class="d-inline-flex">
                            <!--            organization-->
                <div class="form-group flex-column col-4">
                    <label for="organization"> Организация</label>
                    <input type="text"
                           id="organization"
                           class="form-control"
                           v-model="$v.form.organization.$model">
                </div>


                <!--            address-->
            <div class="form-group flex-column col-4">
                <label for="address" >
                    Рабочий адресс  </label>
                <input type="text" id="address" v-model="$v.form.address.$model" class="form-control">

            </div>

            <!--            phone-->
            <div class="form-group flex-column col-4" >
                <label for="phone"> Контактный номер </label>
                <input type="text"
                       id="phone"
                       class="form-control"
                       v-model="$v.form.phone.$model">
            </div>
        </div>


       <div class=" col-5">
           <!--            country-->

               <div class="form-group d-flex flex-column">
                   <label for="edit-field-country-und" >
                       Страна</label>
                   <input id="edit-field-country-und"
                          name="country"
                          class="form-control required"
                          v-model="$v.form.country.$model"
                          list="countryList">
                   <datalist id = countryList>
                       <option value="" selected disabled>Выберите страну </option>
                       <option v-for="country in countryList"
                               :key="country.code"
                               :selected="country.code==='BY'" :value="country.name"></option>
                   </datalist>
               </div>

           <!--            degree-->
           <div class="form-group d-flex flex-column">
               <label for="degree">Ученая степень</label>
               <select type="text"
                       id="degree"
                       class="form-control" v-model="$v.form.degree.$model">
                   <option value="" disabled selected>Выберите ученую степень</option>
                   <option v-for="degree in allDegree" :value="degree.id" :key="degree.id" >{{degree.name}}</option>
               </select>

           </div>
           <!--            sections-->
           <div class="form-group d-flex flex-column " >
               <label for="sections"></label>
               Секции в которых вы будете принимать участие. <br>
               <small>* Для выборанескольких зажмите Ctrl</small>
               <select name="sections"
                       id="sections"
                       class="from-control"
                       v-model="form.selectedSectionIds"
                       multiple>
                   <option v-for="section in allSections"
                           :value="section.id"
                           :key="section.id">{{section.name}}</option>
               </select></div>

           <div class="text-left" v-if="message!==''">
               <small class="form__error">
                   *{{message}}
                   <br>
               </small>
           </div>
       </div>

        <br>
        <button type="submit" class="btn btn-primary" @click="registration" :disabled="$v.$invalid"> Регистрация </button>
        <router-link class="btn btn-link" to="/">Отмена</router-link>
    </div>



</template>

<script>
    import AuthenticationService from '../services/authenticate/authentication-service';
    import {required, minLength, maxLength, email, sameAs} from "vuelidate/lib/validators";
    import {submitState} from "../services/submit-state";
    import ThrowableExceptions from "../services/authenticate/exceptions/throwable-exceptions";
    import passwordSymbolsValidator from "../customValidators/password-symbols-validator.js";

    const countryList = require('country-list');

    export default {
        name: "Registration",
        data() {
            return {
                form:{
                    email: 'ivan@ico.com',
                    password: 'asd123!',
                    confirmPassword: 'asd123!',
                    name:'Иван',
                    surname:'Иванов',
                    patronymic:'Иванович',
                    country:'Ivanovo',
                    address:'Dzerzhinskogo',
                    organization:'BSUIR',
                    degree:[],
                    phone:'+37529123456789',
                    selectedSectionIds:[]
                },
                submitStatus: submitState.NOT_SUBMIT,
                message:'',
                allSections:[],
                allDegree:[]

            }
        },
        validations: {
            form: {
                password: {
                    required,
                    minLength: minLength(6),
                    maxLength: maxLength(20),
                    isPasswordRightSymbols: passwordSymbolsValidator
                },
                confirmPassword: {
                    required,
                    sameAsPassword: sameAs('password'),
                },
                email: {
                    email,
                    required
                },
                address: {required},
                name: {},
                surname: {},
                patronymic: {},
                country: {},
                organization: {},
                degree:{},
                phone: {}
            }
        },
        methods: {
            registration: function () {

                if (this.$v.$invalid) {
                    this.$v.$touch();
                    this.submitStatus = submitState.ERROR

                } else {
                    const regData =  {
                        email : this.form.email,
                        password : this.form.password,
                        confirmPassword : this.form.confirmPassword,
                        name : this.form.name,
                        surname : this.form.surname,
                        patronymic : this.form.patronymic,
                        phone : this.form.phone,
                        countryCode : countryList.getCode(this.form.country),
                        jobAddress: this.form.address,
                        degreeId : this.form.degree,
                        sectionsIds : this.form.selectedSectionIds,
                        organization: this.form.organization,
                    };


                    AuthenticationService.registration(regData)
                        .then(registrationResponseData => {
                            console.log(registrationResponseData);
                            this.$router.push('/login');
                        })
                        .catch(e =>{
                            this.message = ThrowableExceptions.getMessage(e);
                        });
                }
            },
            setMessage(val){
                this.message = val;
            }
        },
        computed:{
            countryList(){
                return countryList.getCodes().map(code=>{
                    return {
                        code: code,
                        name: countryList.getName(code)
                    }
                });
            }
        },
        created() {
            this.allSections = this.$store.getters.ALL_SECTIONS;
            this.allDegree = this.$store.getters.ALL_DEGREES;
        }
    }


</script>

<style>
    .form__error{
        color: red;
    }
</style>
