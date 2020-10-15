<template>
    <div>
       <div id="report-collection-upload">
           <div class="form-group d-flex flex-column">
               <label for="year" class="mt-2"> Год</label>
               <input
                       type="text"
                       id="year"
                       class="form-control col-2 "
                       v-model="form.year">

               <label for="description"  class="mt-2"> Описание</label>
               <textarea
                       id="description"
                       class="form-control text-left"
                       v-model="form.description" />

               <label for="reports-collection">Файл сборника докладов</label>
               <input type="file"
                      id="reports-collection"
                      class="form-control-file "
                      ref="reportFile" @change="changeFileInput">

               <button class="btn-primary btn col-2 mt-2" @click="uploadCollection">Загрузить</button>

           </div>
       </div>

        <div class="mt-3">
            <label for="report-collections"> Загруженые сборники</label>
            <div id="report-collections" class="">

                <ul class="list-group">
                    <li v-for="collection in collections" :key="collection.id" class="list-group-item p-1 m-1">
                        <div class="flex-row">
                            <span></span>
                            <button @click="downloadCollection(collection.id)" class="btn btn-link">{{collection.year}}</button>
                            <span>{{collection.description}}</span>
                            <button class="btn btn-link float-right" @click="remove(collection.id)">удалить</button>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>

</template>

<script>

    import ArchiveService from "../../services/archive/archive-service";

    export default {
        name: "UploadReportsCollection",
        data(){
            return {
                form:{
                    year:Number.parseInt(new Date().getFullYear()),
                    file: null,
                    description:''
                },
                collections:[
                ]
            }
        },
        methods:{
            changeFileInput(){
                this.form.file = this.$refs.reportFile.files[0];
            },
            uploadCollection(){
                let fdata = new FormData();
                fdata.append('file',this.form.file);
                fdata.append('year',this.form.year);
                fdata.append('description',this.form.description);

                let service  = new ArchiveService(this.$store.getters.TOKEN);

                service.uploadFiles(fdata).then(data=>{
                    console.log(data);
                    this.collections.push(data);
                });
            },
            downloadCollection(id){
                let service  = new ArchiveService(this.$store.getters.TOKEN);
                service.download(id);
            },
            remove(id){
                let service  = new ArchiveService(this.$store.getters.TOKEN);
                service.remove(id).then(data=>{
                    this.collections = this.collections.filter(c=>c.id!==data)
                });
            }



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