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
        <el-form-item  :label="$t('apk.name')">
          <span v-if="dialogStatus=='delete'">{{temp.name}}</span>
          <div v-else>
            <input id="upload-input"  ref="upload-input" type="file" accept=".apk" class="c-hide"
                   @change="handleFileChange">
            <div id="drop">
              <span v-if="this.tempFile === undefined" v-text="$t('base.fileInputHint')" ></span>
              <span v-else>{{temp.name}}</span>
              <el-button style="margin-left:16px;" size="mini" type="primary" @click="handleBrowse" v-text="$t('base.browse')"></el-button>
            </div>
            <div>
              <el-button  v-if="this.tempFile !== undefined" size="mini" type="danger" @click="handleUpload" v-text="$t('base.upload')"></el-button>
            </div>
          </div>
        </el-form-item>
        <!--版本-->
        <el-form-item :label="$t('apk.version')" prop="version">
          <span v-if="dialogStatus=='delete'">{{temp.version}}</span>
          <el-input v-else v-model="temp.version"></el-input>
        </el-form-item>
        <!-- 描述 -->
        <el-form-item :label="$t('apk.description')" prop="description">
          <span v-if="dialogStatus=='delete'">{{temp.description}}</span>
          <el-input v-else v-model="temp.description"></el-input>
        </el-form-item>
        <!-- 重要性 -->
        <el-form-item :label="$t('table.importance')" prop="importance">
          <el-rate style="margin-top:8px;" v-model="temp.importance" :colors="['#99A9BF', '#F7BA2A', '#FF9900']" :max='5'></el-rate>
        </el-form-item>
        <!-- 立即激活 -->
        <el-form-item v-if="dialogStatus!='delete'" :label="$t('role.enableImmediately')" prop="enable">
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
  import { apkList, addApk, updateApk, deleteApk, checkApkMd5, uploadApk } from '@/api/apk'
  import waves from '@/directive/waves' // 水波纹指令
  import { parseTime } from '@/utils'

  var browserMD5File = require('browser-md5-file')

  export default {
    name: 'apkManager',
    directives: {
      waves
    },
    data() {
      return {
        tableKey: 0,
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
          description: undefined,
          enable: true
        },
        tempFile: undefined,
        uploadStatus: undefined,
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
        this.tempFile = undefined
        this.uploadStatus = false
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
            if (this.uploadStatus === false) {
              this.$notify({
                title: '失败',
                message: '请先上传文件',
                type: 'error',
                duration: 2000
              })
            } else {
              addApk(this.temp).then(() => {
                // this.list.unshift(this.temp)
                this.resetTemp()
                this.getList()
                this.dialogFormVisible = false
                this.$notify({
                  title: '成功',
                  message: '创建成功',
                  type: 'success',
                  duration: 2000
                })
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
      handleFileChange(e) {
        const files = e.target.files
        const select = files[0] // only use files[0]
        if (!select) {
          this.temp.name = undefined
          this.tempFile = undefined
        } else {
          this.temp.name = select.name
          this.tempFile = select
        }
        this.$refs['upload-input'].value = null // fix can't select the same excel

        console.log(select)
      },
      handleBrowse() {
        document.getElementById('upload-input').click()
      },
      // 文件上传
      handleUpload() {
        // 检查MD5 判断上传状态
        var _this = this // 共享变量 或者用 =>
        browserMD5File(this.tempFile, function(err, md5) {
          if (err) {
            console.log(err)
            return
          }
          const param = {
            'md5': md5
          }
          console.log('_status ' + _this.uploadStatus)

          checkApkMd5(param).then((resp) => {
            const data = resp.data
            if (data.success === true) {
              const code = data.code
              if (code === 2000) {
                this.uploadFile(md5, 0, 0)
              } else if (code === 2001) {
                console.log('文件已存在')
                _this.uploadStatus = true
              } else if (code === 2002) {
                this.uploadFile(md5, 0, 0)
              }
            } else {
              console.log('上传失败')
            }
          })
        })
      // checkApkMd5(form).then(() => {
      //   this.status.fileUpload = true
      //   console.log('上传成功')
      // })
      //
      // var form = new FormData()
      // form.append('file', this.tempFile)
      // uploadApk(form).then(() => {
      //   this.status.fileUpload = true
      //   console.log('上传成功')
      // })
      },
      // 上传Apk
      uploadFile(md5, chunks, chunk) {
        var form = new FormData()
        form.append('file', this.tempFile)
        form.append('uid', this.temp.uid)
        form.append('id', 'taskId')
        form.append('name', this.temp.name)
        form.append('chunks', chunks)
        form.append('chunk', chunk)
        form.append('md5', md5)
        uploadApk(form).then(() => {
          this.status.fileUpload = true
          console.log('上传成功')
        })
      },
      formatJson(filterVal, jsonData) {
        return jsonData.map(v => filterVal.map(j => {
          if (j === 'timestamp') {
            return parseTime(v[j])
          } else {
            return v[j]
          }
        }))
      }
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
