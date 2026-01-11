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
				<el-form-item class="input" v-if="type!='info'"  label="体重(kg)" prop="weight" >
					<el-input-number v-model="ruleForm.weight" placeholder="体重(kg)" :disabled="ro.weight"></el-input-number>
				</el-form-item>
				<el-form-item v-else class="input" label="体重(kg)" prop="weight" >
					<el-input v-model="ruleForm.weight" placeholder="体重(kg)" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="心率(b/min)" prop="heartrate" >
					<el-input v-model.number="ruleForm.heartrate" placeholder="心率(b/min)" clearable  :readonly="ro.heartrate"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="心率(b/min)" prop="heartrate" >
					<el-input v-model="ruleForm.heartrate" placeholder="心率(b/min)" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="血压(mmHg)" prop="bloodpressure" >
					<el-input v-model.number="ruleForm.bloodpressure" placeholder="血压(mmHg)" clearable  :readonly="ro.bloodpressure"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="血压(mmHg)" prop="bloodpressure" >
					<el-input v-model="ruleForm.bloodpressure" placeholder="血压(mmHg)" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="血糖(mmol/L)" prop="bloodsugar" >
					<el-input-number v-model="ruleForm.bloodsugar" placeholder="血糖(mmol/L)" :disabled="ro.bloodsugar"></el-input-number>
				</el-form-item>
				<el-form-item v-else class="input" label="血糖(mmol/L)" prop="bloodsugar" >
					<el-input v-model="ruleForm.bloodsugar" placeholder="血糖(mmol/L)" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="血脂(mmol/L)" prop="bloodlipid" >
					<el-input-number v-model="ruleForm.bloodlipid" placeholder="血脂(mmol/L)" :disabled="ro.bloodlipid"></el-input-number>
				</el-form-item>
				<el-form-item v-else class="input" label="血脂(mmol/L)" prop="bloodlipid" >
					<el-input v-model="ruleForm.bloodlipid" placeholder="血脂(mmol/L)" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="运动时间(h)" prop="exercisetime" >
					<el-input-number v-model="ruleForm.exercisetime" placeholder="运动时间(h)" :disabled="ro.exercisetime"></el-input-number>
				</el-form-item>
				<el-form-item v-else class="input" label="运动时间(h)" prop="exercisetime" >
					<el-input v-model="ruleForm.exercisetime" placeholder="运动时间(h)" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="步数" prop="steps" >
					<el-input v-model.number="ruleForm.steps" placeholder="步数" clearable  :readonly="ro.steps"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="步数" prop="steps" >
					<el-input v-model="ruleForm.steps" placeholder="步数" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="运动距离(km)" prop="exercisedistance" >
					<el-input-number v-model="ruleForm.exercisedistance" placeholder="运动距离(km)" :disabled="ro.exercisedistance"></el-input-number>
				</el-form-item>
				<el-form-item v-else class="input" label="运动距离(km)" prop="exercisedistance" >
					<el-input v-model="ruleForm.exercisedistance" placeholder="运动距离(km)" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="睡眠时间(h)" prop="sleeptime" >
					<el-input-number v-model="ruleForm.sleeptime" placeholder="睡眠时间(h)" :disabled="ro.sleeptime"></el-input-number>
				</el-form-item>
				<el-form-item v-else class="input" label="睡眠时间(h)" prop="sleeptime" >
					<el-input v-model="ruleForm.sleeptime" placeholder="睡眠时间(h)" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="用户账号" prop="useraccount" >
					<el-input v-model="ruleForm.useraccount" placeholder="用户账号" clearable  :readonly="ro.useraccount"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="用户账号" prop="useraccount" >
					<el-input v-model="ruleForm.useraccount" placeholder="用户账号" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="用户姓名" prop="username" >
					<el-input v-model="ruleForm.username" placeholder="用户姓名" clearable  :readonly="ro.username"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="用户姓名" prop="username" >
					<el-input v-model="ruleForm.username" placeholder="用户姓名" readonly></el-input>
				</el-form-item>
				<el-form-item class="upload" v-if="type!='info' && !ro.headportrait" label="头像" prop="headportrait" >
					<file-upload
						tip="点击上传头像"
						action="file/upload"
						:limit="3"
						:multiple="true"
						:fileUrls="ruleForm.headportrait?ruleForm.headportrait:''"
						@change="headportraitUploadChange"
					></file-upload>
				</el-form-item>
				<el-form-item class="upload" v-else-if="ruleForm.headportrait" label="头像" prop="headportrait" >
					<img v-if="ruleForm.headportrait.substring(0,4)=='http'&&ruleForm.headportrait.split(',w').length>1" class="upload-img" style="margin-right:20px;" v-bind:key="index" :src="ruleForm.headportrait" width="100" height="100">
					<img v-else-if="ruleForm.headportrait.substring(0,4)=='http'" class="upload-img" style="margin-right:20px;" v-bind:key="index" :src="ruleForm.headportrait.split(',')[0]" width="100" height="100">
					<img v-else class="upload-img" style="margin-right:20px;" v-bind:key="index" v-for="(item,index) in ruleForm.headportrait.split(',')" :src="$base.url+item" width="100" height="100">
				</el-form-item>
				<el-form-item class="date" v-if="type!='info'" label="记录时间" prop="recordtime" >
					<el-date-picker
						format="yyyy 年 MM 月 dd 日"
						value-format="yyyy-MM-dd"
						v-model="ruleForm.recordtime" 
						type="date"
						:readonly="ro.recordtime"
						placeholder="记录时间"
					></el-date-picker> 
				</el-form-item>
				<el-form-item class="input" v-else-if="ruleForm.recordtime" label="记录时间" prop="recordtime" >
					<el-input v-model="ruleForm.recordtime" placeholder="记录时间" readonly></el-input>
				</el-form-item>
			</template>
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
    

	</div>
</template>
<script>
	import { 
		isNumber,
		isIntNumer,
	} from "@/utils/validate";
	export default {
		data() {
			var validateNumber = (rule, value, callback) => {
				if(!value){
					callback();
				} else if (!isNumber(value)) {
					callback(new Error("请输入数字"));
				} else {
					callback();
				}
			};
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
					weight : false,
					heartrate : false,
					bloodpressure : false,
					bloodsugar : false,
					bloodlipid : false,
					exercisetime : false,
					steps : false,
					exercisedistance : false,
					sleeptime : false,
					useraccount : false,
					username : false,
					headportrait : false,
					recordtime : false,
				},
			
				ruleForm: {
					weight: '',
					heartrate: '',
					bloodpressure: '',
					bloodsugar: '',
					bloodlipid: '',
					exercisetime: '',
					steps: '',
					exercisedistance: '',
					sleeptime: '',
					useraccount: '',
					username: '',
					headportrait: '',
					recordtime: '',
				},

				rules: {
					weight: [
						{ validator: validateNumber, trigger: 'blur' },
					],
					heartrate: [
						{ validator: validateIntNumber, trigger: 'blur' },
					],
					bloodpressure: [
						{ validator: validateIntNumber, trigger: 'blur' },
					],
					bloodsugar: [
						{ validator: validateNumber, trigger: 'blur' },
					],
					bloodlipid: [
						{ validator: validateNumber, trigger: 'blur' },
					],
					exercisetime: [
						{ validator: validateNumber, trigger: 'blur' },
					],
					steps: [
						{ validator: validateIntNumber, trigger: 'blur' },
					],
					exercisedistance: [
						{ validator: validateNumber, trigger: 'blur' },
					],
					sleeptime: [
						{ validator: validateNumber, trigger: 'blur' },
					],
					useraccount: [
					],
					username: [
					],
					headportrait: [
					],
					recordtime: [
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
			this.ruleForm.recordtime = this.getCurDate()
		},
		methods: {
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
						if(o=='weight'){
							this.ruleForm.weight = obj[o];
							this.ro.weight = true;
							continue;
						}
						if(o=='heartrate'){
							this.ruleForm.heartrate = obj[o];
							this.ro.heartrate = true;
							continue;
						}
						if(o=='bloodpressure'){
							this.ruleForm.bloodpressure = obj[o];
							this.ro.bloodpressure = true;
							continue;
						}
						if(o=='bloodsugar'){
							this.ruleForm.bloodsugar = obj[o];
							this.ro.bloodsugar = true;
							continue;
						}
						if(o=='bloodlipid'){
							this.ruleForm.bloodlipid = obj[o];
							this.ro.bloodlipid = true;
							continue;
						}
						if(o=='exercisetime'){
							this.ruleForm.exercisetime = obj[o];
							this.ro.exercisetime = true;
							continue;
						}
						if(o=='steps'){
							this.ruleForm.steps = obj[o];
							this.ro.steps = true;
							continue;
						}
						if(o=='exercisedistance'){
							this.ruleForm.exercisedistance = obj[o];
							this.ro.exercisedistance = true;
							continue;
						}
						if(o=='sleeptime'){
							this.ruleForm.sleeptime = obj[o];
							this.ro.sleeptime = true;
							continue;
						}
						if(o=='useraccount'){
							this.ruleForm.useraccount = obj[o];
							this.ro.useraccount = true;
							continue;
						}
						if(o=='username'){
							this.ruleForm.username = obj[o];
							this.ro.username = true;
							continue;
						}
						if(o=='headportrait'){
							this.ruleForm.headportrait = obj[o];
							this.ro.headportrait = true;
							continue;
						}
						if(o=='recordtime'){
							this.ruleForm.recordtime = obj[o];
							this.ro.recordtime = true;
							continue;
						}
					}
				}
				// 获取用户信息
				this.$http({
					url: `${this.$storage.get('sessionTable')}/session`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						var json = data.data;
						if(((json.useraccount!=''&&json.useraccount) || json.useraccount==0) && this.$storage.get("role")!="管理员"){
							this.ruleForm.useraccount = json.useraccount
							this.ro.useraccount = true;
						}
						if(((json.username!=''&&json.username) || json.username==0) && this.$storage.get("role")!="管理员"){
							this.ruleForm.username = json.username
							this.ro.username = true;
						}
						if(((json.headportrait!=''&&json.headportrait) || json.headportrait==0) && this.$storage.get("role")!="管理员"){
							this.ruleForm.headportrait = json.headportrait
							this.ro.headportrait = true;
						}
					} else {
						this.$message.error(data.msg);
					}
				});
			
			},
			// 多级联动参数

			info(id) {
				this.$http({
					url: `healthrecord/info/${id}`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						this.ruleForm = data.data;
						//解决前台上传图片后台不显示的问题
						let reg=new RegExp('../../../upload','g')//g代表全部
					} else {
						this.$message.error(data.msg);
					}
				});
			},

			// 提交
			async onSubmit() {
					if(this.ruleForm.headportrait!=null) {
						this.ruleForm.headportrait = this.ruleForm.headportrait.replace(new RegExp(this.$base.url,"g"),"");
					}
					var objcross = this.$storage.getObj('crossObj');
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
									}
								}
							}
							
							await this.$http({
								url: `healthrecord/${!this.ruleForm.id ? "save" : "update"}`,
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
											this.parent.healthrecordCrossAddOrUpdateFlag = false;
											this.parent.search();
											this.parent.contentStyleChange();
										}
									});
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
				this.parent.healthrecordCrossAddOrUpdateFlag = false;
				this.parent.contentStyleChange();
			},
			headportraitUploadChange(fileUrls) {
				this.ruleForm.headportrait = fileUrls;
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
