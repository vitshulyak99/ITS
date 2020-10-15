<template>
    <div>
        <div class="container">
            <label for="report-collections"> Сборники прошлых конференций</label>
            <div id="report-collections" class="">

                <ul class="list-group">
                    <li v-for="collection in collections" :key="collection.id" class="list-group-item p-1 m-1">
                        <div class="flex-row">
                            <span></span>
                            <button @click="downloadCollection(collection.id)" class="btn btn-link">{{collection.year}}</button>
                            <span>{{collection.description}}</span>

                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</template>

<script>
    import ArchiveService from "../services/archive/archive-service";

    export default {
        name: "Archive",
        data(){
            return {
                collections:[]
            }
        },
        methods:{
            downloadCollection(id){
                let service  = new ArchiveService(this.$store.getters.TOKEN);
                service.download(id);
            },
        },
        created() {
            let service  = new ArchiveService(this.$store.getters.TOKEN);
            service.all().then(data=>{
                data.forEach(d=>this.collections.push(d));
            });
        }
    }
</script>

<style scoped>

</style>