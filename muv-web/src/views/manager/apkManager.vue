<!-- apk管理 -->
<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item"
                :placeholder="$t('table.title')" v-model="listQuery.title">
      </el-input>
      <el-select clearable style="width: 90px" class="filter-item" v-model="listQuery.importance"
                 :placeholder="$t('table.importance')">
        <el-option v-for="item in importanceOptions" :key="item" :label="item" :value="item">
        </el-option>
      </el-select>
      <el-select clearable class="filter-item" style="width: 130px" v-model="listQuery.type"
                 :placeholder="$t('table.type')">
        <el-option v-for="item in  enableOptions" :key="item.key" :label="item.display_name+'('+item.key+')'"
                   :value="item.key">
        </el-option>
      </el-select>
      <el-select @change='handleFilter' style="width: 140px" class="filter-item" v-model="listQuery.sort">
        <el-option v-for="item in sortOptions" :key="item.key" :label="item.label" :value="item.key">
        </el-option>
      </el-select>
      <el-button class="filter-item" type="primary" v-waves icon="el-icon-search" @click="handleFilter">
        {{$t('table.search')}}
      </el-button>
      <!-- 添加 -->
      <el-button class="filter-item" style="margin-left: 10px;" @click="handleCreate" type="primary"
                 icon="el-icon-edit">{{$t('table.add')}}
      </el-button>
      <el-checkbox class="filter-item" style='margin-left:15px;' @change='tableKey=tableKey+1' v-model="showReviewer">
        {{$t('table.reviewer')}}
      </el-checkbox>
    </div>


    <!--角色展示表-->
    <el-table :key='tableKey' :data="list" v-loading="listLoading" element-loading-text="给我一点时间" border fit
              highlight-current-row
              style="width: 100%">
      <!--ID-->
      <el-table-column align="center" :label="$t('apk.id')" width="65">
        <template slot-scope="scope">
          <span>{{scope.row.id}}</span>
        </template>
      </el-table-column>
      <!--名称-->
      <el-table-column align="center" :label="$t('apk.name')" min-width="65px">
        <template slot-scope="scope">
          <span>{{scope.row.name}}</span>
        </template>
      </el-table-column>
      <!--版本-->
      <el-table-column align="center" :label="$t('apk.version')" min-width="65px">
        <template slot-scope="scope">
          <span>{{scope.row.version}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" :label="$t('apk.path')" min-width="65px">
        <template slot-scope="scope">
          <span>{{scope.row.path}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" :label="$t('apk.description')" min-width="65px">
        <template slot-scope="scope">
          <span>{{scope.row.description}}</span>
        </template>
      </el-table-column>
      <!--状态-->
      <el-table-column class-name="status-col" align="center" :label="$t('apk.enable')" width="100px">
        <template slot-scope="scope">
          <el-tag :type="scope.row.enable | enableFilter">{{handleEnable(scope.row.enable)}}</el-tag>
        </template>
      </el-table-column>
      <!--编辑-->
      <el-table-column align="center" :label="$t('role.actions')" width="230" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="handleUpdate(scope.row)">{{$t('table.edit')}}</el-button>
          <el-button v-if="scope.row.enable!='deleted'" size="mini" type="danger" @click="handleDelete(scope.row)">
            {{$t('table.delete')}}
          </el-button>
        </template>
      </el-table-column>

    </el-table>

    <div class="pagination-container">
      <el-pagination background @size-change="handleSizeChange" @current-change="handleCurrentChange"
                     :current-page="listQuery.pageIndex" :page-sizes="[10,20,30, 50]" :page-size="listQuery.pageSize"
                     layout="total, sizes, prev, pager, next, jumper" :total="total">
      </el-pagination>
    </div>

    <!-- 编辑框 -->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form :rules="rules" ref="dataForm" :model="temp" label-position="left" label-width="100px"
               style='width: 400px; margin-left:50px;'>
        <el-form-item :label="$t('apk.name')">
          <span v-if="dialogStatus!=='create'">{{temp.name}}</span>
          <div v-else>
            <uploader
              browse_button="browse_button"
              :url="server_config.url+'/manager/apk/upload/'"
              :multi_selection="false"
              chunk_size="2MB"
              :FilesAdded="filesAdded"
              :BeforeUpload="beforeUpload"
              @inputUploader="inputUploader"
              :FileUploaded="fileUploaded"
            />
            <span v-for="file in files">{{file.name}}</span>
            <el-button id="browse_button" type="primary" size="mini">选择文件</el-button>
            <el-button type="danger" @click="uploadStart()" size="mini">开始上传</el-button>
            <div>
              <el-progress v-if="files.length>0" :text-inside="true" :stroke-width="20" :percentage="files[0].percent"></el-progress>
            </div>
          </div>
        </el-form-item>
        <!--版本-->
        <el-form-item :label="$t('apk.version')" prop="version">
          <span v-if="dialogStatus==='delete'">{{temp.version}}</span>
          <el-input v-else v-model="temp.version"></el-input>
        </el-form-item>
        <!-- 描述 -->
        <el-form-item :label="$t('apk.description')" prop="description">
          <span v-if="dialogStatus==='delete'">{{temp.description}}</span>
          <el-input v-else v-model="temp.description"></el-input>
        </el-form-item>
        <!-- 重要性 -->
        <el-form-item :label="$t('table.importance')" prop="importance">
          <el-rate v-if="dialogStatus==='delete'" disabled="disabled" style="margin-top:8px;" v-model="temp.importance" :colors="['#99A9BF', '#F7BA2A', '#FF9900']" :max='5'></el-rate>
          <el-rate v-else style="margin-top:8px;" v-model="temp.importance" :colors="['#99A9BF', '#F7BA2A', '#FF9900']" :max='5'></el-rate>
        </el-form-item>
        <!-- 立即激活 -->
        <el-form-item v-if="dialogStatus!=='delete'" :label="$t('role.enableImmediately')" prop="enable">
          <el-select class="filter-item" v-model="temp.enable" placeholder="Please select">
            <el-option v-for="item in  enableOptions" :key="item.key" :label="item.display_name"
                       :value="item.key">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">{{$t('table.cancel')}}</el-button>
        <el-button v-if="dialogStatus=='create'" type="primary" @click="createData">{{$t('table.confirm')}}</el-button>
        <el-button v-else-if="dialogStatus=='update'" type="primary" @click="updateData">{{$t('table.confirm')}}
        </el-button>
        <el-button v-else-if="dialogStatus=='delete'" type="primary" @click="deleteData">{{$t('table.confirm')}}
        </el-button>
      </div>
    </el-dialog>

    <el-dialog title="Reading statistics" :visible.sync="dialogPvVisible">
      <el-table :data="pvData" border fit highlight-current-row style="width: 100%">
        <el-table-column prop="key" label="Channel"></el-table-column>
        <el-table-column prop="pv" label="Pv"></el-table-column>
      </el-table>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogPvVisible = false">{{$t('table.confirm')}}</el-button>
      </span>
    </el-dialog>

  </div>
</template>

<script>
  import { apkList, addApk, updateApk, deleteApk } from '@/api/apk'
  import waves from '@/directive/waves' // 水波纹指令
  import { parseTime } from '@/utils'
  import Uploader from '@/views/uploader/Uploader'
  import Cookie from 'js-cookie'

  var browserMD5File = require('browser-md5-file')

  export default {
    name: 'apkManager',
    directives: {
      waves
    },
    data() {
      return {
        tableKey: 0,
        server_config: this.global.server_config,
        up: {},
        files: [],
        uploadTag: undefined,
        list: null,
        total: null,
        listLoading: true,
        listQuery: {
          pageIndex: 1,
          pageSize: 20
        },
        importanceOptions: [1, 2, 3],
        enableOptions: [
          { key: true, display_name: this.$t('base.yes') },
          { key: false, display_name: this.$t('base.no') }
        ],
        sortOptions: [{ label: 'ID Ascending', key: '+id' }, { label: 'ID Descending', key: '-id' }],
        showReviewer: false,
        temp: {
          userId: undefined,
          name: undefined,
          version: undefined,
          importance: 1,
          path: undefined,
          description: undefined,
          enable: true
        },
        rules: {
          name: [{ required: true, message: '请选择文件', trigger: 'blur' }],
          version: [{ required: true, message: 'version is required', trigger: 'change' }],
          importance: [{ required: true, message: 'importance is required', trigger: 'change' }],
          description: [{ required: true, message: 'description is required', trigger: 'blur' }],
          enable: [{ required: true, message: 'enable is required', trigger: 'change' }]
        },
        dialogFormVisible: false,
        dialogStatus: '',
        textMap: {
          update: 'Edit',
          create: 'Create',
          delete: 'Delete'
        },
        dialogPvVisible: false,
        pvData: [],
        downloadLoading: false
      }
    },
    computed: {
      yes() {
        return this.$t('yes')
      },
      status() {
        return this.files.length > 0 ? this.files[0].status : null
      }
    },
    watch: {
      status() {
        if (this.status === 5) {
          this.uploadTag = true
          // this.temp.name = this.files[0].name
          this.$notify({
            title: '提示',
            message: '文件上传成功',
            type: 'success',
            duration: 2000
          })
        }
      },
      dialogFormVisible() {
        if (this.dialogFormVisible === false) {
          this.resetTemp()
        }
      }
    },
    filters: {
      enableFilter(status) {
        const enableMap = {
          true: 'success',
          false: 'danger'
        }
        return enableMap[status]
      }
    },
    created() {
      this.getList()
    },
    methods: {
      getList() {
        this.listLoading = true
        apkList(this.listQuery).then(response => {
          const resp = response.data // 返回数据
          this.list = resp.data.pageInfo.list
          this.total = resp.data.pageInfo.total
          this.listLoading = false
        })
      },
      handleEnable(enable) {
        if (enable) {
          return this.$t('base.yes')
        } else {
          return this.$t('base.no')
        }
      },
      handleFilter() {
        this.listQuery.pageIndex = 1
        this.getList()
      },
      handleSizeChange(val) {
        this.listQuery.pageSize = val
        this.getList()
      },
      handleCurrentChange(val) {
        this.listQuery.pageIndex = val
        this.getList()
      },
      handleModifyStatus(row, status) {
        this.$message({
          message: this.$t('base.operateSuccess'),
          type: 'success'
        })
        row.status = status
      },
      resetTemp() {
        this.temp = {
          userId: undefined,
          name: undefined,
          version: undefined,
          importance: 1,
          description: undefined,
          enable: true
        }
        this.files = []
        this.uploadTag = false
      },
      handleCreate() {
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].clearValidate()
        })
      },
      createData() {
        this.$refs['dataForm'].validate((valid) => {
          console.log(this.temp)
          if (valid) {
            if (this.uploadTag === true) {
              const user = JSON.parse(Cookie.get('user-info'))
              var uid = user.uid
              this.temp.userId = uid
              addApk(this.temp).then(() => {
                // this.list.unshift(this.temp)
                this.getList()
                this.dialogFormVisible = false
                this.$notify({
                  title: '成功',
                  message: '创建成功',
                  type: 'success',
                  duration: 2000
                })
              })
            } else {
              this.$notify({
                title: '失败',
                message: '请先上传文件',
                type: 'error',
                duration: 2000
              })
            }
          }
        })
      },
      handleUpdate(row) {
        this.temp = Object.assign({}, row) // copy obj
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].clearValidate()
        })
      },
      updateData() {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            const tempData = Object.assign({}, this.temp)
            updateApk(tempData).then(() => {
              this.getList()
              this.dialogFormVisible = false
              this.$notify({
                title: '成功',
                message: '更新成功',
                type: 'success',
                duration: 2000
              })
            })
          }
        })
      },
      handleDelete(row) {
        this.temp = Object.assign({}, row) // copy obj
        this.dialogStatus = 'delete'
        this.dialogFormVisible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].clearValidate()
        })
      },
      deleteData() {
        const id = this.temp.id
        if (id !== undefined) {
          deleteApk(id).then(() => {
            this.getList()
            this.dialogFormVisible = false
            this.$notify({
              title: '成功',
              message: '删除成功',
              type: 'success',
              duration: 2000
            })
          })
        } else {
          this.$notify({
            title: '失败',
            message: '删除失败',
            type: 'error',
            duration: 2000
          })
        }
      },
      formatJson(filterVal, jsonData) {
        return jsonData.map(v => filterVal.map(j => {
          if (j === 'timestamp') {
            return parseTime(v[j])
          } else {
            return v[j]
          }
        }))
      },
      filesAdded(up, files) {
        if (up.files.length > 1) {
          up.removeFile(up.files[0])
        }
        browserMD5File(up.files[0].getNative(), function(err, md5) {
          if (err) {
            console.log(err)
            return
          }
          up.files[0]['md5'] = md5.toUpperCase()
          up.files[0].status = 1
          console.log('md5=====>' + md5)
        })
        this.files = files
        this.temp.name = files[0].name
        console.log('file Add:' + this.files)
      },
      inputUploader(up) {
        this.up = up
        this.files = up.files
      },
      beforeUpload(up, file) {
        up.setOption('multipart_params', { 'size': file.size, 'md5': file.md5 })
      },
      uploadStart() {
        this.up.start()
      },
      fileUploaded(uploader, file, resp) {
        this.temp.path = resp.response
        console.log('All: ' + resp.response)
      }
    },
    components: {
      'uploader': Uploader
    }
  }
</script>

<style scoped>
  #upload-input{
    display: none;
    z-index: -9999;
  }
  #drop{
    border: 2px dashed #bbb;
    margin: 0px auto;
    padding: 5px;
    font-size: 20px;
    border-radius: 5px;
    text-align: center;
    color: #bbb;
    position: relative;
  }
</style>
