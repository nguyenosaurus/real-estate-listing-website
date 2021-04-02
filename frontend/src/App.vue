<template>
  <div id="app">
      <div>
            <label>Select Province:</label>
            <select @change='getDistricts()' v-model='province'>
                <option value='' >Select Province</option>
                <option v-for='data in provinces' :value='data.name' :key='data.name'>{{ data.name }}</option>
            </select>
        </div>

        <div>
            <label>Select District:</label>
            <select v-model='district' @change='getWards()'>
                <option value='' >Select District</option>
                <option v-for='data in districts' :value='data.name' :key='data.name'>{{ data.name }}</option>
            </select>
        </div>

        <div>
            <label>Select Ward:</label>
            <select v-model='ward'>
                <option value='' >Select Ward</option>
                <option v-for='data in wards' :value='data.name' :key='data.name'>{{ data.name }}</option>
            </select>
        </div>

            <div id='project'>
                <label>Select Project:</label>
                <select v-model='project'>
                    <option value='' >Select Project</option>
                    <option v-for='data in projects' :value='data.project' :key='data.project_id'>{{ data.project }}</option>
                </select>
            </div>

            <div id='property_type'>
                <label>Select Property:</label>
                <select v-model='property'>
                    <option value='' >Select Property</option>
                    <option v-for='data in properties' :value='data.propertyType' :key='data.propertyTypeId'>{{ data.propertyType }}</option>
                </select>
            </div>

            <div id='transaction_type'>
                <label>Select Transaction Type:</label>
                <select v-model='transaction'>
                    <option value='' >Select Transaction Type</option>
                    <option v-for='data in transactions' :value='data.transaction_type' :key='data.transaction_type_id'>{{ data.transaction_type }}</option>
                </select>
            </div>

            <div class="output">Price: {{ price.value }}</div>
            <Slider
            v-model="price.value"
            v-bind="price">
            </Slider>

            <div class="output">Area: {{ area.value }}</div>
            <Slider
            v-model="area.value"
            v-bind="area">
            </Slider>
            <button v-on:click="search()">Search</button>
    <router-view :key="$route.fullPath"/>
  </div>
</template>
<style src="@vueform/slider/themes/default.css"></style>
<script>
import axios from "axios";
import store from "@/store.js";
import Slider from '@vueform/slider/dist/slider.vue2.js'
export default {
name: 'Home',
components: { Slider },
data() { return {
    provinces: store.provinces,
    province: '',
    districts: [],
    district: '',
    wards: [],
    ward: '',
    projects: [],
    properties: [],
    transactions: [],
    project: '',
    property: '',
    transaction: '',
    price: {
    value: [0, 10000000],
    max: 10000000
    },
    area: {
    value: [0, 310000],
    max: 310000
    }
  }},
  methods: {
      getProvinces: function(){
          axios.get('http://localhost:9000/showCity')
          .then(function (response) {
             this.provinces = response.data;
          }.bind(this));
      },
      getDistricts: function() {
        this.districts = store.districts.filter(
            district => district.province_name === this.province
        )
      },
      getWards: function() {
        this.wards = store.wards.filter(
            ward => ward.province_name === this.province && ward.district_name === this.district
        )
      },
      getProjects: function(){
          axios.get('http://localhost:9000/showProject')
          .then(function (response) {
             this.projects = response.data;
          }.bind(this));
      },
      getProperties: function(){
          axios.get('http://localhost:9000/showPropertyType')
          .then(function (response) {
             this.properties = response.data;
          }.bind(this));
      },
      getTransactions: function(){
          axios.get('http://localhost:9000/showTransactionType')
          .then(function (response) {
             this.transactions = response.data;
          }.bind(this));
      },
      search: function() {
          var provinceText = (this.province) ? this.province.toLowerCase().replace(/(huyện |thành phố |thị xã |quận |phường |thị trấn |xã |tỉnh )/,'') : "";
          var districtText = (this.district) ? "/"+this.district.toLowerCase().replace(/(huyện |thành phố |thị xã |quận |phường |thị trấn |xã |tỉnh )/,'') : "";
          var wardText = (this.ward) ? "/"+this.ward.toLowerCase().replace(/(huyện |thành phố |thị xã |quận |phường |thị trấn |xã |tỉnh )/,'') : "";
          var projectText = this.project;
          var propertyText = this.property;
          var transactionText = this.transaction;
          this.$router.push("/"+provinceText+districtText+wardText+"?project="+projectText+"&property="+propertyText+"&transaction="+transactionText+"&priceGT="+this.price.value[0]+"&priceLT="+this.price.value[1]+"&areaGT="+this.area.value[0]+"&areaLT="+this.area.value[1]);
      }
  },
  created: function(){
      this.getProjects();
      this.getProperties();
      this.getTransactions();
  }
};
</script>
