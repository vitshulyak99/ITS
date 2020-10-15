<template>
    <div class="d-flex ">
        <div id="mySadBar" class="m-1 navbar-nav list-group sidebar_with d-flex" >
            <button
                    class="text-center nav-item list-group-item btn-link"
                    @click="()=>{this.focusCaseId=0}">Список докладов</button>
            <button
                    class="text-center nav-item list-group-item btn-link"
                    @click="()=>{this.focusCaseId=1}">Рецензии</button>

<!--            <button -->
<!--                    class="text-center nav-item list-group-item btn-link"-->
<!--                    @click="()=>{this.focusCaseId=2}">Скачать доклады</button>-->
        </div>
        <div  class=" ml-2 border-primary col-9 border-radius-10 border ">
            <reviewer-reports-list :allSections="allSections" v-if="focusCaseId===0"/>
            <review-list v-if="focusCaseId===1"/>
            <chair-download :all-sections="allSections" v-if="focusCaseId===2"/>

        </div>
    </div>
</template>

<script>
    import ReviewerReportsList from "./ReviewerReportsList";
    import ReviewList from "./ReviewList";
    import ChairDownload from "./ChairDownload";
    export default {
        name: "ReviewerMain",
        components: {ChairDownload, ReviewList, ReviewerReportsList},
        data(){
            return {
                focusCaseId:0,
            }
        },
        computed:{

            allSections(){
                return this.$store.getters.ALL_SECTIONS;
            }
        }
    }
</script>

<style scoped>

</style>