<template>
    <div class="test-container">
      <h2>配送员管理测试</h2>
      
      <!-- 创建/更新表单 -->
      <div class="form-section">
        <h3>配送员操作</h3>
        <input v-model="form.workId" placeholder="工号" required>
        <input v-model="form.name" placeholder="姓名">
        <input v-model="form.age" type="number" placeholder="年龄">
        
        <div class="button-group">
          <button @click="submitDeliver" class="submit-btn">提交</button>
          <button @click="clearForm" class="clear-btn">清空</button>
        </div>
      </div>
  
      <!-- 操作区 -->
      <div class="action-section">
        <button @click="fetchAllDelivers" class="fetch-btn">获取所有配送员</button>
        <input 
          v-model="searchId" 
          placeholder="输入工号查询"
          @keyup.enter="fetchDeliverById"
        >
      </div>
  
      <!-- 结果显示 -->
      <div class="result-section">
        <h3>配送员列表</h3>
        <div v-if="delivers.length === 0" class="empty-tip">暂无数据</div>
        <div v-for="d in delivers" :key="d.workId" class="deliver-item">
          <div class="info">
            <span class="work-id">工号: {{ d.workId }}</span>
            <span>姓名: {{ d.name }}</span>
            <span>年龄: {{ d.age }}</span>
          </div>
          <div class="actions">
            <button @click="editDeliver(d)" class="edit-btn">编辑</button>
            <button @click="deleteDeliver(d.workId)" class="delete-btn">删除</button>
          </div>
        </div>
      </div>
  
      <!-- 响应信息 -->
      <div v-if="responseMessage" class="response-message">
        <pre>{{ responseMessage }}</pre>
      </div>
    </div>
  </template>
  
  <script>
  import axios from 'axios'
  
  export default {
    data() {
      return {
        form: {
          workId: '',
          name: '',
          age: null
        },
        searchId: '',
        delivers: [],
        responseMessage: null
      }
    },
    methods: {
      // 获取所有配送员
      async fetchAllDelivers() {
        try {
          const res = await axios.get('/api/deliver')
          this.delivers = res.data
          this.responseMessage = `获取成功，共 ${res.data.length} 条数据`
        } catch (err) {
          this.handleError(err, '获取配送员列表失败')
        }
      },
  
      // 根据ID查询
      async fetchDeliverById() {
        if (!this.searchId) return
        try {
          const res = await axios.get(`/api/deliver/${this.searchId}`)
          this.delivers = [res.data]
          this.responseMessage = '查询成功'
        } catch (err) {
          this.handleError(err, '查询配送员失败')
        }
      },
  
      // 提交表单（创建/更新）
      async submitDeliver() {
        try {
          const isUpdate = !!this.form.workId
          const method = isUpdate ? 'put' : 'post'
          const url = isUpdate 
            ? `/api/deliver/${this.form.workId}`
            : '/api/deliver'
  
          const res = await axios[method](url, this.form)
          this.responseMessage = `${isUpdate ? '更新' : '创建'}成功: ${JSON.stringify(res.data, null, 2)}`
          this.fetchAllDelivers()
          if (!isUpdate) this.clearForm()
        } catch (err) {
          this.handleError(err, '提交失败')
        }
      },
  
      // 删除配送员
      async deleteDeliver(workId) {
        if (!confirm(`确定删除工号 ${workId} 的配送员？`)) return
        try {
          await axios.delete(`/api/deliver/${workId}`)
          this.responseMessage = `工号 ${workId} 删除成功`
          this.fetchAllDelivers()
        } catch (err) {
          this.handleError(err, '删除失败')
        }
      },
  
      // 编辑配送员
      editDeliver(deliver) {
        this.form = { ...deliver }
      },
  
      // 清空表单
      clearForm() {
        this.form = { workId: '', name: '', age: null }
        this.responseMessage = null
      },
  
      // 统一错误处理
      handleError(err, defaultMsg) {
        const message = err.response?.data?.message || err.message
        alert(`${defaultMsg}: ${message}`)
        console.error(err)
      }
    }
  }
  </script>
  
  <style scoped>
  .test-container {
    max-width: 800px;
    margin: 2rem auto;
    padding: 1rem;
    border: 1px solid #e1e1e1;
    border-radius: 8px;
  }
  
  .form-section {
    margin-bottom: 2rem;
    padding: 1rem;
    background: #f8f9fa;
    border-radius: 6px;
  }
  
  input {
    display: block;
    width: 100%;
    padding: 0.5rem;
    margin: 0.5rem 0;
    border: 1px solid #ddd;
    border-radius: 4px;
  }
  
  .button-group {
    margin-top: 1rem;
    display: flex;
    gap: 0.5rem;
  }
  
  button {
    padding: 0.5rem 1rem;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    transition: opacity 0.3s;
  }
  
  button:hover {
    opacity: 0.8;
  }
  
  .submit-btn { background: #67C23A; color: white; }
  .clear-btn { background: #909399; color: white; }
  .fetch-btn { background: #409EFF; color: white; }
  .edit-btn { background: #E6A23C; color: white; }
  .delete-btn { background: #F56C6C; color: white; }
  
  .deliver-item {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 0.8rem;
    margin: 0.5rem 0;
    border: 1px solid #eee;
    border-radius: 4px;
  }
  
  .info {
    flex: 1;
    display: flex;
    gap: 1rem;
  }
  
  .work-id {
    font-weight: bold;
    min-width: 120px;
  }
  
  .actions {
    display: flex;
    gap: 0.5rem;
  }
  
  .response-message {
    margin-top: 1rem;
    padding: 1rem;
    background: #f4f4f5;
    border-radius: 4px;
  }
  
  .empty-tip {
    color: #909399;
    text-align: center;
    padding: 1rem;
  }
  </style>