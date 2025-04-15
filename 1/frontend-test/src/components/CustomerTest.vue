<template>
  <div class="test-container">
    <h2>客户管理测试</h2>
    
    <!-- 创建表单 -->
    <div class="form-section">
      <h3>创建/更新客户</h3>
      <input v-model="form.customerid" placeholder="客户ID">
      <input v-model="form.name" placeholder="姓名">
      <input v-model="form.age" type="number" placeholder="年龄">
      <input v-model="form.address" placeholder="地址">
      <input v-model="form.numOfOrder" type="number" placeholder="订单数量">
      <input v-model="form.password" type="password" placeholder="密码">
      
      <button @click="submitCustomer">提交</button>
    </div>

    <!-- 操作按钮 -->
    <div class="action-buttons">
      <button @click="fetchAllCustomers">获取所有客户</button>
    </div>

    <!-- 结果显示 -->
    <div class="result-section">
      <h3>客户列表</h3>
      <div v-for="c in customers" :key="c.customerid" class="customer-item">
        <p>ID: {{ c.customerid }} - 姓名: {{ c.name }}</p>
        <button @click="deleteCustomer(c.customerid)">删除</button>
      </div>
      <pre v-if="responseData">{{ responseData }}</pre>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  data() {
    return {
      form: {
        customerid: '',
        name: '',
        age: null,
        address: '',
        numOfOrder: null,
        password: ''
      },
      customers: [],
      responseData: null
    }
  },
  methods: {
    async fetchAllCustomers() {
      try {
        const res = await axios.get('/api/customer')
        this.customers = res.data
      } catch (err) {
        alert('获取失败: ' + err.response.data)
      }
    },
    async submitCustomer() {
      try {
        /**const method = this.form.customerid ? 'put' : 'post'
        const url = `/api/customer/${this.form.customerid || ''}`
        
        const res = await axios[method](url, this.form) **/
        const isUpdate = !!this.form.customerid; // 明确判断是否更新
        const method = isUpdate ? 'put' : 'post';
        const url = isUpdate 
          ? `/api/customer/${this.form.customerid}` // PUT必须带ID
          : '/api/customer'; // POST不带ID

        const res = await axios[method](url, this.form);
        
        this.responseData = res.data
        this.fetchAllCustomers()
      } catch (err) {
        alert('操作失败: ' + err.response.data)
      }
    },
    async deleteCustomer(id) {
      if (confirm(`确定删除客户 ${id}?`)) {
        try {
          await axios.delete(`/api/customer/${id}`)
          this.fetchAllCustomers()
        } catch (err) {
          alert('删除失败: ' + err.response.data)
        }
      }
    }
  }
}
</script>

<style scoped>
/* 样式同上，可根据需要调整 */
</style>