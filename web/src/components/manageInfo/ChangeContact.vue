<template>
    <div >

       <div class="d-inline-flex">
           <!--            organization-->
           <div class="form-group flex-column ">
               <label for="organization"> Организация</label>
               <input type="text"
                      id="organization"
                      class="form-control"
                      v-model="$v.form.organization.$model">
           </div>


           <!--            address-->
           <div class="form-group flex-column ml-4 ">
               <label for="address" >
                   Рабочий адресс  </label>
               <input type="text" id="address" v-model="$v.form.address.$model" class="form-control">

           </div>

           <!--            phone-->
           <div class="form-group flex-column ml-4  " >
               <label for="phone"> Контактный номер </label>
               <input type="text"
                      id="phone"
                      class="form-control"
                      v-model="$v.form.phone.$model">
           </div>
       </div>
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
        <button

                class="btn btn-primary" @click="update" :disabled="$v.invalid && !$v.dirty()">
            Сохранить изменения
        </button>


    </div>
</template>

<script>

    import AccountService from "../../services/account/account-service";

    const countryList = require('country-list');

    export default {
        name: "ChangeContact",
        data(){
            return {
                form:{
                    country:'Ivanovo',
                    address:'Dzerzhinskogo',
                    organization:'BSUIR',
                    phone:'+37529123456789',
                    degree:''
                },
            }
        },
        validations: {
            form: {
                address: {},
                country: {},
                organization: {},
                degree:{},
                phone: {}
            }
        },
        computed:{
            allDegree(){
                return this.$store.getters.ALL_DEGREES;
            },
            countryList(){
                return countryList.getCodes().map(code=>{
                    return {
                        code: code,
                        name: countryList.getName(code)
                    }
                });
            }
        },
        methods:{
            update(){
                let service = new AccountService(this.$store.getters.TOKEN);

                service.updateInfo(this.form.organization,this.form.address,this.form.phone,this.form.country,this.form.degree);

            }
        }
    }
</script>

<style scoped>

</style>