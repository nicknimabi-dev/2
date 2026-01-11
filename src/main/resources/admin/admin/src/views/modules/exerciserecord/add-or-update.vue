<template>
	<div class="addEdit-block">
		<el-form
			class="add-update-preview"
			ref="ruleForm"
			:model="ruleForm"
			:rules="rules"
			label-width="180px"
		>
			<template >
				<el-form-item class="input" v-if="type!='info'"  label="计划标题" prop="plantitle" >
					<el-input v-model="ruleForm.plantitle" placeholder="计划标题" clearable  :readonly="ro.plantitle"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="计划标题" prop="plantitle" >
					<el-input v-model="ruleForm.plantitle" placeholder="计划标题" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="打卡次数" prop="checkintimes" style="display:none">
					<el-input v-model.number="ruleForm.checkintimes" placeholder="打卡次数" clearable  :readonly="ro.checkintimes"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="打卡次数" prop="checkintimes" style="display:none">
					<el-input v-model="ruleForm.checkintimes" placeholder="打卡次数" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="运动名称" prop="exercisename" >
					<el-input v-model="ruleForm.exercisename" placeholder="运动名称" clearable  :readonly="ro.exercisename"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="运动名称" prop="exercisename" >
					<el-input v-model="ruleForm.exercisename" placeholder="运动名称" readonly></el-input>
				</el-form-item>
				<el-form-item class="upload" v-if="type!='info' && !ro.images" label="图片" prop="images" >
					<file-upload
						tip="点击上传图片"
						action="file/upload"
						:limit="3"
						:multiple="true"
						:fileUrls="ruleForm.images?ruleForm.images:''"
						@change="imagesUploadChange"
					></file-upload>
				</el-form-item>
				<el-form-item class="upload" v-else-if="ruleForm.images" label="图片" prop="images" >
					<img v-if="ruleForm.images.substring(0,4)=='http'&&ruleForm.images.split(',w').length>1" class="upload-img" style="margin-right:20px;" v-bind:key="index" :src="ruleForm.images" width="100" height="100">
					<img v-else-if="ruleForm.images.substring(0,4)=='http'" class="upload-img" style="margin-right:20px;" v-bind:key="index" :src="ruleForm.images.split(',')[0]" width="100" height="100">
					<img v-else class="upload-img" style="margin-right:20px;" v-bind:key="index" v-for="(item,index) in ruleForm.images.split(',')" :src="$base.url+item" width="100" height="100">
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="运动类型" prop="exercisetype" >
					<el-input v-model="ruleForm.exercisetype" placeholder="运动类型" clearable  :readonly="ro.exercisetype"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="运动类型" prop="exercisetype" >
					<el-input v-model="ruleForm.exercisetype" placeholder="运动类型" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="运动距离" prop="exercisedistance" >
					<el-input v-model="ruleForm.exercisedistance" placeholder="运动距离" clearable  :readonly="ro.exercisedistance"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="运动距离" prop="exercisedistance" >
					<el-input v-model="ruleForm.exercisedistance" placeholder="运动距离" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="运动时长" prop="exerciseduration" >
					<el-input v-model="ruleForm.exerciseduration" placeholder="运动时长" clearable  :readonly="ro.exerciseduration"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="运动时长" prop="exerciseduration" >
					<el-input v-model="ruleForm.exerciseduration" placeholder="运动时长" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="用户账号" prop="useraccount" style="display:none">
					<el-input v-model="ruleForm.useraccount" placeholder="用户账号" clearable  :readonly="ro.useraccount"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="用户账号" prop="useraccount" style="display:none">
					<el-input v-model="ruleForm.useraccount" placeholder="用户账号" readonly></el-input>
				</el-form-item>
				<el-form-item class="date" v-if="type!='info'" label="记录日期" prop="recorddate" >
					<el-date-picker
						value-format="yyyy-MM-dd HH:mm:ss"
						v-model="ruleForm.recorddate" 
						type="datetime"
						:readonly="ro.recorddate"
						placeholder="记录日期"
					></el-date-picker>
				</el-form-item>
				<el-form-item class="input" v-else-if="ruleForm.recorddate" label="记录日期" prop="recorddate" >
					<el-input v-model="ruleForm.recorddate" placeholder="记录日期" readonly></el-input>
				</el-form-item>
			</template>
			<el-form-item class="input" v-if="type!='info'"  label="起点" prop="startingpoint" >
				<el-input @click.native="openMapDiolag('startingpoint')"  v-model="ruleForm.startingpoint2" placeholder="请选择起点"></el-input>
			</el-form-item>
			<el-form-item class="input" v-else-if="ruleForm.startingpoint" label="起点" prop="startingpoint" >
				<el-input v-model="ruleForm.startingpoint2" placeholder="请选择起点" readonly></el-input>
			</el-form-item>
			<el-form-item class="input" v-if="type!='info'"  label="终点" prop="endpoint" >
				<el-input @click.native="openMapDiolag('endpoint')"  v-model="ruleForm.endpoint2" placeholder="请选择终点"></el-input>
			</el-form-item>
			<el-form-item class="input" v-else-if="ruleForm.endpoint" label="终点" prop="endpoint" >
				<el-input v-model="ruleForm.endpoint2" placeholder="请选择终点" readonly></el-input>
			</el-form-item>
			<el-form-item class="textarea" v-if="type!='info'" label="运动内容" prop="exercisecontent" >
				<el-input
					style="min-width: 200px; max-width: 600px;"
					type="textarea"
					:rows="8"
					placeholder="运动内容"
					v-model="ruleForm.exercisecontent" >
				</el-input>
			</el-form-item>
			<el-form-item v-else-if="ruleForm.exercisecontent" label="运动内容" prop="exercisecontent" >
				<span class="text">{{ruleForm.exercisecontent}}</span>
			</el-form-item>
			<el-form-item class="btn">
				<el-button class="btn3"  v-if="type!='info'" type="success" @click="onSubmit">
					<span class="icon iconfont icon-xihuan"></span>
					提交
				</el-button>
				<el-button class="btn4" v-if="type!='info'" type="success" @click="back()">
					<span class="icon iconfont icon-xihuan"></span>
					取消
				</el-button>
				<el-button class="btn5" v-if="type=='info'" type="success" @click="back()">
					<span class="icon iconfont icon-xihuan"></span>
					返回
				</el-button>
			</el-form-item>
		</el-form>
    
		<el-dialog
			width="50%"
			title="坐标查询"
			:visible.sync="mapVisiable"
			append-to-body>
			<el-amap-search-box class="search-box" :on-search-result="onSearchResult" :search-option="searchOption" ></el-amap-search-box>
			<div class="amap-wrapper">
				<el-amap class="amap-box" :vid="'amap-vue'" 
				:center="center"
				:zoom="zoom"
				:plugin="plugin"
				:events="events" >
				<el-amap-marker v-bind:key="marker" v-for="marker in markers" :position="marker" :events="events"></el-amap-marker>
				</el-amap>
			</div>
			<div>坐标：[{{ refColumn?(ruleForm[refColumn]?ruleForm[refColumn].split(';')[0]:''):ruleForm.longitude }}, {{ refColumn?(ruleForm[refColumn]?ruleForm[refColumn].split(';')[1]:''):ruleForm.latitude }}],地址：{{refColumn?ruleForm[refColumn + '2']:ruleForm.fulladdress}}</div>
		</el-dialog>

	</div>
</template>
<script>
	import { 
		isIntNumer,
	} from "@/utils/validate";
	import Vue from 'vue'
	import VueAMap from 'vue-amap'
	window._AMapSecurityConfig = {
		securityJsCode: "8848c1c64023c00cf192ce109b97243c",
	};
	export default {
		data() {
			let self = this
			var validateIntNumber = (rule, value, callback) => {
				if(!value){
					callback();
				} else if (!isIntNumer(value)) {
					callback(new Error("请输入整数"));
				} else {
					callback();
				}
			};
			return {
				id: '',
				type: '',
			
			
				ro:{
					plantitle : false,
					checkintimes : false,
					exercisename : false,
					images : false,
					exercisetype : false,
					exercisedistance : false,
					exerciseduration : false,
					startingpoint : false,
					endpoint : false,
					exercisecontent : false,
					useraccount : false,
					recorddate : false,
				},
				refColumn: '',
				// 地图
				mapVisiable: false,
				zoom: 12,
				center: [116.410426, 39.934946],
				markers: [],
				searchOption: {
					citylimit: false
				},
				plugin: [{
					// 定位城市
					pName: "CitySearch",
					events: {
						init(o) {
							// o是高德地图定位插件实例
							o.getLocalCity(function(status, result) {
								if (status === "complete" && result.info === "OK") {
									// 查询成功，result即为当前所在城市信息
									self.center = [result.rectangle.split(';')[0].split(',')[0], result.rectangle.split(';')[0].split(',')[1]];
								}
							});
						}
					}
				}, ],
				events: {
					click (e) {
						let { lng, lat } = e.lnglat
						if(self.refColumn){
							
						}else {
							self.ruleForm.longitude = lng
							self.ruleForm.latitude = lat
						}
						
						// 这里通过高德 SDK 完成。
						/* eslint-disable */
						var geocoder = new AMap.Geocoder({
							radius: 1000,
							extensions: 'all'
						})
						geocoder.getAddress([lng, lat], function (status, result) {
							if (status === 'complete' && result.info === 'OK') {
								if (result && result.regeocode) {
									self.mapVisiable = false
									if(self.refColumn){
										self.ruleForm[self.refColumn] = lng + ';' + lat + ';' + result.regeocode.formattedAddress
										self.ruleForm[self.refColumn + '2'] = result.regeocode.formattedAddress
									}else {
										self.ruleForm.fulladdress = result.regeocode.formattedAddress
									}
									
								}
							}
						})
					}
				},
			
				ruleForm: {
					plantitle: '',
					checkintimes: Number('1'),
					exercisename: '',
					images: '',
					exercisetype: '',
					exercisedistance: '',
					exerciseduration: '',
					startingpoint: '',
					endpoint: '',
					exercisecontent: '',
					useraccount: '',
					recorddate: '',
				},

				rules: {
					plantitle: [
					],
					checkintimes: [
						{ validator: validateIntNumber, trigger: 'blur' },
					],
					exercisename: [
						{ required: true, message: '运动名称不能为空', trigger: 'blur' },
					],
					images: [
					],
					exercisetype: [
					],
					exercisedistance: [
					],
					exerciseduration: [
					],
					startingpoint: [
						{ required: true, message: '起点不能为空', trigger: 'blur' },
					],
					endpoint: [
						{ required: true, message: '终点不能为空', trigger: 'blur' },
					],
					exercisecontent: [
					],
					useraccount: [
					],
					recorddate: [
					],
				},
			};
		},
		props: ["parent"],
		computed: {



		},
		components: {
		},
		created() {
			this.ruleForm.recorddate = this.getCurDateTime()
		},
		mounted() {
			Vue.use(VueAMap)
			VueAMap.initAMapApiLoader({
				key: '7a7b5d185ffd9ca30f582ff9349716ea',
				plugin: ['AMap.Autocomplete', 'AMap.PlaceSearch', 'AMap.Scale', 'AMap.OverView', 'AMap.ToolBar', 'AMap.MapType', 'AMap.PolyEditor', 'AMap.CircleEditor', 'AMap.Geocoder','AMap.CitySearch'],
				// 默认高德 sdk 版本为 1.4.4
				v: '1.4.4'
			})
		},
		methods: {
			// 打开坐标定位窗口
			openMapDiolag (value=null) {
				if(value){
					this.refColumn = value
				}else{
					this.refColumn = ''
				}
				this.mapVisiable = true
			},
			// 查询
			onSearchResult (pois) {
				let latSum = 0;
				let lngSum = 0;
				if (pois.length > 0) {
					pois.forEach(poi => {
						let {lng, lat} = poi;
						lngSum += lng;
						latSum += lat;
						this.markers.push([poi.lng, poi.lat]);
					});
					let center = {
						lng: lngSum / pois.length,
						lat: latSum / pois.length
					};
					this.center = [center.lng, center.lat];
				}
			},
			// 下载
			download(file){
				window.open(`${file}`)
			},
			// 初始化
			init(id,type) {
				if (id) {
					this.id = id;
					this.type = type;
				}
				if(this.type=='info'||this.type=='else'||this.type=='msg'){
					this.info(id);
				}else if(this.type=='logistics'){
					for(let x in this.ro) {
						this.ro[x] = true
					}
					this.logistics=false;
					this.info(id);
				}else if(this.type=='cross'){
					var obj = this.$storage.getObj('crossObj');
					for (var o in obj){
						if(o=='plantitle'){
							this.ruleForm.plantitle = obj[o];
							this.ro.plantitle = true;
							continue;
						}
						if(o=='checkintimes'){
							this.ruleForm.checkintimes = obj[o];
							this.ro.checkintimes = true;
							continue;
						}
						if(o=='exercisename'){
							this.ruleForm.exercisename = obj[o];
							this.ro.exercisename = true;
							continue;
						}
						if(o=='images'){
							this.ruleForm.images = obj[o];
							this.ro.images = true;
							continue;
						}
						if(o=='exercisetype'){
							this.ruleForm.exercisetype = obj[o];
							this.ro.exercisetype = true;
							continue;
						}
						if(o=='exercisedistance'){
							this.ruleForm.exercisedistance = obj[o];
							this.ro.exercisedistance = true;
							continue;
						}
						if(o=='exerciseduration'){
							this.ruleForm.exerciseduration = obj[o];
							this.ro.exerciseduration = true;
							continue;
						}
						if(o=='startingpoint'){
							this.ruleForm.startingpoint2 = obj[o].split(';')[2]
							this.ruleForm.startingpoint = obj[o];
							this.ro.startingpoint = true;
							continue;
						}
						if(o=='endpoint'){
							this.ruleForm.endpoint2 = obj[o].split(';')[2]
							this.ruleForm.endpoint = obj[o];
							this.ro.endpoint = true;
							continue;
						}
						if(o=='exercisecontent'){
							this.ruleForm.exercisecontent = obj[o];
							this.ro.exercisecontent = true;
							continue;
						}
						if(o=='useraccount'){
							this.ruleForm.useraccount = obj[o];
							this.ro.useraccount = true;
							continue;
						}
						if(o=='recorddate'){
							this.ruleForm.recorddate = obj[o];
							this.ro.recorddate = true;
							continue;
						}
					}
					this.ruleForm.checkintimes = Number('1'); 				}
				// 获取用户信息
				this.$http({
					url: `${this.$storage.get('sessionTable')}/session`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						var json = data.data;
						if(this.$storage.get("role")!="管理员") {
							this.ro.checkintimes = true;
						}
						if(((json.useraccount!=''&&json.useraccount) || json.useraccount==0) && this.$storage.get("role")!="管理员"){
							this.ruleForm.useraccount = json.useraccount
							this.ro.useraccount = true;
						}
					} else {
						this.$message.error(data.msg);
					}
				});
			
			},
			// 多级联动参数

			info(id) {
				this.$http({
					url: `exerciserecord/info/${id}`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						this.ruleForm = data.data;
						//解决前台上传图片后台不显示的问题
						let reg=new RegExp('../../../upload','g')//g代表全部
						this.ruleForm.startingpoint2 = this.ruleForm.startingpoint.split(';')[2]
						this.ruleForm.endpoint2 = this.ruleForm.endpoint.split(';')[2]
					} else {
						this.$message.error(data.msg);
					}
				});
			},

			// 提交
			async onSubmit() {
					if(this.ruleForm.images!=null) {
						this.ruleForm.images = this.ruleForm.images.replace(new RegExp(this.$base.url,"g"),"");
					}
					var objcross = this.$storage.getObj('crossObj');
					var table = this.$storage.getObj('crossTable');
					if(objcross!=null) {
						if(!this.ruleForm.checkintimes){
							this.$message.error("打卡次数不能为空");
							return
						}
						objcross.checkintimes = parseFloat(objcross.checkintimes) + parseFloat(this.ruleForm.checkintimes)
											}
					if(!this.ruleForm.id) {
						delete this.ruleForm.userid
					}
					await this.$refs["ruleForm"].validate(async valid => {
						if (valid) {
							if(this.type=='cross'){
								var statusColumnName = this.$storage.get('statusColumnName');
								var statusColumnValue = this.$storage.get('statusColumnValue');
								if(statusColumnName!='') {
									var obj = this.$storage.getObj('crossObj');
									if(statusColumnName && !statusColumnName.startsWith("[")) {
										for (var o in obj){
											if(o==statusColumnName){
												obj[o] = statusColumnValue;
											}
										}
										var table = this.$storage.get('crossTable');
										await this.$http({
											url: `${table}/update`,
											method: "post",
											data: obj
										}).then(({ data }) => {});
										await this.$http({
											url: `${table}/update`,
											method: "post",
											data: objcross
										}).then(({ data }) => {});
									}
								}
							}
							
							await this.$http({
								url: `exerciserecord/${!this.ruleForm.id ? "save" : "update"}`,
								method: "post",
								data: this.ruleForm
							}).then(async ({ data }) => {
								if (data && data.code === 0) {
									this.$message({
										message: "操作成功",
										type: "success",
										duration: 1500,
										onClose: () => {
											this.parent.showFlag = true;
											this.parent.addOrUpdateFlag = false;
											this.parent.exerciserecordCrossAddOrUpdateFlag = false;
											this.parent.search();
											this.parent.contentStyleChange();
										}
									});
									var table = this.$storage.get('crossTable');
									await this.$http({
										url: `${table}/update`,
										method: "post",
										data: objcross
									}).then(({ data }) => {});
								} else {
									this.$message.error(data.msg);
								}
							});
						}
					});
			},
			// 获取uuid
			getUUID () {
				return new Date().getTime();
			},
			// 返回
			back() {
				this.parent.showFlag = true;
				this.parent.addOrUpdateFlag = false;
				this.parent.exerciserecordCrossAddOrUpdateFlag = false;
				this.parent.contentStyleChange();
			},
			imagesUploadChange(fileUrls) {
				this.ruleForm.images = fileUrls;
			},
		}
	};
</script>
<style lang="scss" scoped>
	.addEdit-block {
		padding: 30px;
		background: none;
		width: 100%;
	}
	.add-update-preview {
		border-radius: 10px;
		padding: 40px 30px;
		background: #ffffff;
		border-color: #eee;
		border-width: 0px 0 0;
		border-style: solid;
	}
	.amap-wrapper {
		width: 100%;
		height: 500px;
	}
	
	.search-box {
		position: absolute;
	}
	
	.el-date-editor.el-input {
		width: auto;
	}
	.add-update-preview /deep/ .el-form-item {
		border: 0px solid #eee;
		padding: 0;
		margin: 0 0 26px 0;
		display: inline-block;
		width: 49%;
	}
	.add-update-preview .el-form-item /deep/ .el-form-item__label {
		padding: 0 10px 0 0;
		color: #6e6e6e;
		font-weight: 500;
		width: 180px;
		font-size: 15px;
		line-height: 40px;
		text-align: right;
	}
	
	.add-update-preview .el-form-item /deep/ .el-form-item__content {
		margin-left: 180px;
	}
	.add-update-preview .el-form-item span.text {
		padding: 0 10px;
		color: #333;
		background: none;
		font-weight: 500;
		display: inline-block;
		font-size: 15px;
		line-height: 40px;
		min-width: 50%;
	}
	
	.add-update-preview .el-input {
		width: 100%;
	}
	.add-update-preview .el-input /deep/ .el-input__inner {
		border: 1px solid #E8E8E8;
		border-radius: 0px;
		padding: 0 12px;
		color: #666;
		background: #fff;
		width: 100%;
		font-size: 15px;
		min-width: 50%;
		height: 40px;
	}
	.add-update-preview .el-input /deep/ .el-input__inner[readonly="readonly"] {
		border: 0px solid #ccc;
		cursor: not-allowed;
		border-radius: 0px;
		padding: 0 12px;
		color: #666;
		background: none;
		width: auto;
		font-size: 15px;
		height: 40px;
	}
	.add-update-preview .el-input-number {
		text-align: left;
		width: 100%;
	}
	.add-update-preview .el-input-number /deep/ .el-input__inner {
		text-align: left;
		border: 1px solid #E8E8E8;
		border-radius: 0px;
		padding: 0 12px;
		color: #666;
		background: #fff;
		width: 100%;
		font-size: 15px;
		min-width: 50%;
		height: 40px;
	}
	.add-update-preview .el-input-number /deep/ .is-disabled .el-input__inner {
		text-align: left;
		border: 0px solid #ccc;
		cursor: not-allowed;
		border-radius: 0px;
		padding: 0 12px;
		color: #666;
		background: none;
		width: auto;
		font-size: 15px;
		height: 40px;
	}
	.add-update-preview .el-input-number /deep/ .el-input-number__decrease {
		display: none;
	}
	.add-update-preview .el-input-number /deep/ .el-input-number__increase {
		display: none;
	}
	.add-update-preview .el-select {
		width: 100%;
	}
	.add-update-preview .el-select /deep/ .el-input__inner {
		border: 1px solid #E8E8E8;
		border-radius: 0px;
		padding: 0 10px;
		color: #666;
		background: #fff;
		width: 100%;
		font-size: 15px;
		height: 40px;
	}
	.add-update-preview .el-select /deep/ .is-disabled .el-input__inner {
		border: 0;
		cursor: not-allowed;
		border-radius: 4px;
		padding: 0 10px;
		color: #666;
		background: none;
		width: auto;
		font-size: 15px;
		height: 34px;
	}
	.add-update-preview .el-date-editor {
		width: 100%;
	}
	.add-update-preview .el-date-editor /deep/ .el-input__inner {
		border: 1px solid #E8E8E8;
		border-radius: 0px;
		padding: 0 10px 0 30px;
		color: #666;
		background: #fff;
		width: 100%;
		font-size: 15px;
		height: 40px;
	}
	.add-update-preview .el-date-editor /deep/ .el-input__inner[readonly="readonly"] {
		border: 0;
		cursor: not-allowed;
		border-radius: 0px;
		padding: 0 10px 0 30px;
		color: #666;
		background: none;
		width: auto;
		font-size: 15px;
		height: 40px;
	}
	.add-update-preview .viewBtn {
		border: 1px solid #E8E8E8;
		cursor: pointer;
		border-radius: 0px;
		padding: 0 15px;
		margin: 0 20px 0 0;
		color: #666;
		background: #fff;
		width: auto;
		font-size: 15px;
		line-height: 34px;
		height: 34px;
		.iconfont {
			margin: 0 2px;
			color: #666;
			font-size: 16px;
			height: 34px;
		}
	}
	.add-update-preview .viewBtn:hover {
		opacity: 0.8;
	}
	.add-update-preview .downBtn {
		border: 1px solid #E8E8E8;
		cursor: pointer;
		border-radius: 0px;
		padding: 0 15px;
		margin: 0 20px 0 0;
		color: #666;
		background: #fff;
		width: auto;
		font-size: 15px;
		line-height: 34px;
		height: 34px;
		.iconfont {
			margin: 0 2px;
			color: #666;
			font-size: 16px;
			height: 34px;
		}
	}
	.add-update-preview .downBtn:hover {
		opacity: 0.8;
	}
	.add-update-preview .unBtn {
		border: 0;
		cursor: not-allowed;
		border-radius: 4px;
		padding: 0 0px;
		margin: 0 20px 0 0;
		outline: none;
		color: #999;
		background: none;
		width: auto;
		font-size: 15px;
		line-height: 40px;
		height: 40px;
		.iconfont {
			margin: 0 2px;
			color: #fff;
			display: none;
			font-size: 14px;
			height: 34px;
		}
	}
	.add-update-preview .unBtn:hover {
		opacity: 0.8;
	}
	.add-update-preview /deep/ .el-upload--picture-card {
		background: transparent;
		border: 0;
		border-radius: 0;
		width: auto;
		height: auto;
		line-height: initial;
		vertical-align: middle;
	}
	
	.add-update-preview /deep/ .upload .upload-img {
		border: 1px solid #E8E8E8;
		cursor: pointer;
		border-radius: 0px;
		color: #666;
		background: #fff;
		width: 90px;
		font-size: 24px;
		line-height: 60px;
		text-align: center;
		height: 60px;
	}
	
	.add-update-preview /deep/ .el-upload-list .el-upload-list__item {
		border: 1px solid #E8E8E8;
		cursor: pointer;
		border-radius: 0px;
		color: #666;
		background: #fff;
		width: 90px;
		font-size: 24px;
		line-height: 60px;
		text-align: center;
		height: 60px;
	}
	
	.add-update-preview /deep/ .el-upload .el-icon-plus {
		border: 1px solid #E8E8E8;
		cursor: pointer;
		border-radius: 0px;
		color: #666;
		background: #fff;
		width: 90px;
		font-size: 24px;
		line-height: 60px;
		text-align: center;
		height: 60px;
	}
	.add-update-preview /deep/ .el-upload__tip {
		color: #666;
		white-space: nowrap;
		font-size: 15px;
	}
	
	.add-update-preview .el-textarea /deep/ .el-textarea__inner {
		border: 1px solid #E8E8E8;
		border-radius: 0px;
		padding: 12px;
		color: #666;
		background: #fff;
		width: 100%;
		font-size: 15px;
		min-height: 150px;
		height: auto;
	}
	.add-update-preview .el-textarea /deep/ .el-textarea__inner[readonly="readonly"] {
				border: 0;
				cursor: not-allowed;
				border-radius: 0px;
				padding: 12px;
				color: #666;
				background: none;
				width: auto;
				font-size: 15px;
				min-width: 400px;
				height: auto;
			}
	.add-update-preview .el-form-item.btn {
		padding: 0;
		margin: 20px 0 0;
		.btn1 {
			border: 0px solid #ccc;
			cursor: pointer;
			border-radius: 6px;
			padding: 0 10px;
			margin: 0 10px 0 0;
			color: #fff;
			background: #56cee3;
			width: auto;
			font-size: 16px;
			min-width: 110px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 40px;
			}
		}
		.btn1:hover {
			opacity: 0.8;
		}
		.btn2 {
			border: 0px solid #ccc;
			cursor: pointer;
			border-radius: 6px;
			padding: 0 10px;
			margin: 0 10px 0 0;
			color: #fff;
			background: #03b6be;
			width: auto;
			font-size: 16px;
			min-width: 110px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 34px;
			}
		}
		.btn2:hover {
			opacity: 0.8;
		}
		.btn3 {
			border: 0px solid #ccc;
			cursor: pointer;
			border-radius: 6px;
			padding: 0 10px;
			margin: 0 10px 0 0;
			color: #fff;
			background: #ebc78a;
			width: auto;
			font-size: 16px;
			min-width: 110px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 40px;
			}
		}
		.btn3:hover {
			opacity: 0.8;
		}
		.btn4 {
			border: 0px solid #ccc;
			cursor: pointer;
			border-radius: 6px;
			padding: 0 10px;
			margin: 0 10px 0 0;
			color: #fff;
			background: #56cee3;
			width: auto;
			font-size: 16px;
			min-width: 110px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 40px;
			}
		}
		.btn4:hover {
			opacity: 0.8;
		}
		.btn5 {
			border: 0px solid #ccc;
			cursor: pointer;
			border-radius: 6px;
			padding: 0 10px;
			margin: 0 10px 0 0;
			color: #fff;
			background: #8faedc;
			width: auto;
			font-size: 16px;
			min-width: 110px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 40px;
			}
		}
		.btn5:hover {
			opacity: 0.8;
		}
	}
</style>
