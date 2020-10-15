<template>
    <div>
        <div class="form-group" >
            <label for="name"> Имя</label>
            <input type="text" id="name" v-model="$v.name.new.$model" class="form-control">

            <label for="surname"> Фамилия</label>
            <input type="text" id="surname" v-model="$v.surname.new.$model" class="form-control">

            <label for="patronymic"> Отчество</label>
            <input type="text" id="patronymic" v-model="$v.patronymic.new.$model" class="form-control">
            <button class="btn btn-primary mt-2" :disabled="!haveChanges || $v.$invalid"  @click = 'saveChanges'>Сохранить изменения</button>
            <button class="btn" v-if="haveChanges">Отмена</button>
        </div>
    </div>
</template>
<script>

    // import AccountService from "../../services/account/account-service";

    import AccountService from "../../services/account/account-service";

    export default {
        name: 'change-name',
        data(){
            return {
                name:{
                    old:'',
                    new:''
                },
                surname:{
                    old:'',
                    new:''
                },
                patronymic:{
                    old:'',
                    new:''
                }
            }
        },
        validations:{
            name:{
                old:{},
                new:{}
            },
            surname:{
                old:{},
                new:{}
            },
            patronymic:{
                old:{},
                new:{}
            }
        },
        methods:{
            setDefault()
            {
                this.name.new = this.name.old;
                this.surname.new = this.surname.old;
                this.patronymic.new = this.patronymic.old;
            },
            updateDefault()
            {
                this.name.old = this.name.new;
                this.surname.old = this.surname.new;
                this.patronymic.old = this.patronymic.new;
            },
            saveChanges(){

                const accountService = new AccountService(this.$store.getters.TOKEN);
                accountService.updateName(
                    this.$store.getters.EMAIL,
                    this.name.new,
                    this.surname.new,
                    this.patronymic.new
                ).then(data => {
                    this.$store.commit('SET_FIO',`${data.name} ${data.surname} ${data.patronymic}`);
                })
            }

        },
        computed:{
          haveChanges(){
             return this.$v.name.new.$model !== this.$v.name.old.$model ||
                 this.$v.surname.new.$model !== this.$v.surname.old.$model ||
                 this.$v.patronymic.new.$model !== this.$v.patronymic.old.$model
          }
        },
        created() {
            let fio  = this.$store.getters.FIO.split(' ');
            this.name.old = fio[0];
            this.surname.old = fio[1];
            this.patronymic.old = fio[2];
            this.name.new = this.name.old;
            this.surname.new = this.surname.old;
            this.patronymic.new = this.patronymic.old;
        }
    }
</script>