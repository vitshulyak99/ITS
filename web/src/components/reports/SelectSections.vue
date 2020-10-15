<template>
    <div class="container">

        <div class="form-group">
            <label>
                <select v-model="selectSections" multiple class="multiselect form-control">
                    <option value="" disabled> Выберите секции</option>
                    <option v-for="section in sections" :key="section.id" :value="section.id">{{section.name}}</option>
                </select>
                <small v-if="selectSections.length>2"> Выберите 2 секции</small>
            </label>
        </div>


            <button class="btn btn-primary" :disabled="selectSections.length>2" @click="choiceSections" > сохранить </button>


            <!--            <pre class="language-json"><code>{{ value  }}</code></pre>-->


    </div>

</template>
<script>
    import ReportsService from "../../services/reports/reports-service";

    export default {
        name: 'select-sections',
        data(){
            return {
                selectSections:[],
                sections:[]
            }
        },
        validations:{
            selectSections: {
                validCount(value){
                    return value.length<3;
                }
            }
        },
        methods:{
            getAllSections(){
                const reportsService = new ReportsService(this.$store.getters.TOKEN);

                reportsService.getSections().then(data=>{
                    console.log(data["sections"]);
                     this.sections = data["sections"];
                })
            },

            choiceSections(){
                const id = this.$store.getters.ID;
                const reportsService = new ReportsService(this.$store.getters.TOKEN);

                console.log(this.selectSections);
                reportsService.choiceSections(id,this.selectSections).then(data=>
                {
                    console.log(data.sections);
                    this.$store.commit('SET_SECTIONS',data.sections);
                })

            }
        },
        created() {
            this.getAllSections();
        }
    }
</script>