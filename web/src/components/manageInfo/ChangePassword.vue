<template>
    <div >
        <div class="form-group">
            <label for="oldPass"> Старый пароль </label>
            <input id="oldPass" v-model="$v.oldPass.$model" class="form-control" type="password">

            <label for="newPass"> Новый пароль </label>
            <input id="newPass" v-model="$v.newPass.$model" class="form-control" type="password">
            <small class="form__error" v-if="$v.newPass.$dirty && !$v.newPass.passwordPattern">
                *Пароль должен содержать a-zA-Z,0-9, ? или ! или _
                <br>
            </small>
            <small class="form__error" v-if="$v.newPass.$dirty && $v.newPass.dontSameAsOldPassword">
                *Пароль не должен совпадать со старым
                <br>
            </small>
            <small class="form__error" v-if="$v.newPass.$dirty && !$v.newPass.maxLength">
                *Максимальная длина {{ $v.newPass.$params.maxLength.max }} символов
                <br>
            </small>
            <small class="form__error"  v-if="$v.newPass.$dirty &&  !$v.newPass.minLength">
                *Минимальная длина {{ $v.newPass.$params.minLength.min }} символов
                <br>
            </small>

            <label for="confirmPass"> Повторите новый пароль </label>
            <input id="confirmPass" v-model="$v.confirmPass.$model" class="form-control" type="password">
            <small class="form__error" v-if="!$v.confirmPass.sameAs && $v.confirmPass.$dirty">
                *Пароли не совпадают
            </small>
        </div>
        <button
                @click="save"
                class="btn btn-primary" :disabled="!$v.$dirty && $v.$invalid">
            Сохранить изменения
        </button>
        <button class="btn" v-if="haveChanges">Отменить</button>
    </div>
</template>
<script>
    import {maxLength, minLength, sameAs} from "vuelidate/lib/validators";
    import AccountService from "../../services/account/account-service";
    import passwordSymbolsValidator from "../../customValidators/password-symbols-validator";


    export default {
        name: 'change-password',
        data(){
            return {
                oldPass:'',
                newPass:'',
                confirmPass:''
            }
        },
        validations:{
            oldPass: {
                minLength: minLength(6),
                maxLength: maxLength(20),
            },
            newPass:{
                minLength: minLength(6),
                maxLength: maxLength(20),
                passwordPattern:passwordSymbolsValidator,
                dontSameAsOldPassword: sameAs('oldPass')
            },
            confirmPass:{
                sameAs: sameAs('newPass')
            }
        },
        methods:{
            save(){
                const accountService = new AccountService(this.$store.getters.TOKEN);
                accountService.changePassword(this.$store.getters.EMAIL, this.oldPass,this.newPass,this.confirmPass)
                .then(data => {
                    alert(data?'пароль успешно изменен':'что-то пошлло не так');
                    this.clearModel();

                }).catch(e=>{
                    if (e.includes("WrongOldPassword")) {
                        alert('Old password not match');
                    }
                })
            },
            clearModel(){
                this.$v.newPass.$model ='';
                this.$v.oldPass.$model = '';
                this.$v.confirmPass.$model = ''
            }
        },
        computed:{
            haveChanges(){
                return '' !== this.$v.newPass.$model ||
                       '' !== this.$v.oldPass.$model ||
                       '' !== this.$v.confirmPass.$model
            }
        }
    }
</script>

<style>

</style>