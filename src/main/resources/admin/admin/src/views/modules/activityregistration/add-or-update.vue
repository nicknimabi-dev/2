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
				<el-form-item class="input" v-if="type!='info'" label="报名编号" prop="registrationnumber" >
					<el-input v-model="ruleForm.registrationnumber" placeholder="报名编号" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-else-if="ruleForm.registrationnumber" label="报名编号" prop="registrationnumber" >
					<el-input v-model="ruleForm.registrationnumber" placeholder="报名编号" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="活动名称" prop="activityname" >
					<el-input v-model="ruleForm.activityname" placeholder="活动名称" clearable  :readonly="ro.activityname"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="活动名称" prop="activityname" >
					<el-input v-model="ruleForm.activityname" placeholder="活动名称" readonly></el-input>
				</el-form-item>
				<el-form-item class="upload" v-if="type!='info' && !ro.activitypicture" label="活动图片" prop="activitypicture" >
					<file-upload
						tip="点击上传活动图片"
						action="file/upload"
						:limit="3"
						:multiple="true"
						:fileUrls="ruleForm.activitypicture?ruleForm.activitypicture:''"
						@change="activitypictureUploadChange"
					></file-upload>
				</el-form-item>
				<el-form-item class="upload" v-else-if="ruleForm.activitypicture" label="活动图片" prop="activitypicture" >
					<img v-if="ruleForm.activitypicture.substring(0,4)=='http'&&ruleForm.activitypicture.split(',w').length>1" class="upload-img" style="margin-right:20px;" v-bind:key="index" :src="ruleForm.activitypicture" width="100" height="100">
					<img v-else-if="ruleForm.activitypicture.substring(0,4)=='http'" class="upload-img" style="margin-right:20px;" v-bind:key="index" :src="ruleForm.activitypicture.split(',')[0]" width="100" height="100">
					<img v-else class="upload-img" style="margin-right:20px;" v-bind:key="index" v-for="(item,index) in ruleForm.activitypicture.split(',')" :src="$base.url+item" width="100" height="100">
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="活动类型" prop="activitytype" >
					<el-input v-model="ruleForm.activitytype" placeholder="活动类型" clearable  :readonly="ro.activitytype"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="活动类型" prop="activitytype" >
					<el-input v-model="ruleForm.activitytype" placeholder="活动类型" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="活动时间" prop="activitytime" >
					<el-input v-model="ruleForm.activitytime" placeholder="活动时间" clearable  :readonly="ro.activitytime"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="活动时间" prop="activitytime" >
					<el-input v-model="ruleForm.activitytime" placeholder="活动时间" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="活动地点" prop="activitylocation" >
					<el-input v-model="ruleForm.activitylocation" placeholder="活动地点" clearable  :readonly="ro.activitylocation"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="活动地点" prop="activitylocation" >
					<el-input v-model="ruleForm.activitylocation" placeholder="活动地点" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="报名人数" prop="activityquota" >
					<el-input v-model.number="ruleForm.activityquota" placeholder="报名人数" clearable  :readonly="ro.activityquota"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="报名人数" prop="activityquota" >
					<el-input v-model="ruleForm.activityquota" placeholder="报名人数" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="报名原因" prop="reasonforregistration" >
					<el-input v-model="ruleForm.reasonforregistration" placeholder="报名原因" clearable  :readonly="ro.reasonforregistration"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="报名原因" prop="reasonforregistration" >
					<el-input v-model="ruleForm.reasonforregistration" placeholder="报名原因" readonly></el-input>
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
				<el-form-item class="input" v-if="type!='info'"  label="联系方式" prop="contactinformation" >
					<el-input v-model="ruleForm.contactinformation" placeholder="联系方式" clearable  :readonly="ro.contactinformation"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="联系方式" prop="contactinformation" >
					<el-input v-model="ruleForm.contactinformation" placeholder="联系方式" readonly></el-input>
				</el-form-item>
				<el-form-item class="date" v-if="type!='info'" label="报名时间" prop="registrationtime" >
					<el-date-picker
						value-format="yyyy-MM-dd HH:mm:ss"
						v-model="ruleForm.registrationtime" 
						type="datetime"
						:readonly="ro.registrationtime"
						placeholder="报名时间"
					></el-date-picker>
				</el-form-item>
				<el-form-item class="input" v-else-if="ruleForm.registrationtime" label="报名时间" prop="registrationtime" >
					<el-input v-model="ruleForm.registrationtime" placeholder="报名时间" readonly></el-input>
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
		isIntNumer,
		isMobile,
	} from "@/utils/validate";
	export default {
		data() {
			var validateMobile = (rule, value, callback) => {
				if(!value){
					callback();
				} else if (!isMobile(value)) {
					callback(new Error("请输入正确的手机号码"));
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
					registrationnumber : false,
					activityname : false,
					activitypicture : false,
					activitytype : false,
					activitytime : false,
					activitylocation : false,
					activityquota : false,
					reasonforregistration : false,
					useraccount : false,
					username : false,
					contactinformation : false,
					registrationtime : false,
					crossuserid : false,
					crossrefid : false,
					sfsh : false,
					shhf : false,
				},
			
				ruleForm: {
					registrationnumber: this.getUUID(),
					activityname: '',
					activitypicture: '',
					activitytype: '',
					activitytime: '',
					activitylocation: '',
					activityquota: Number('1'),
					reasonforregistration: '',
					useraccount: '',
					username: '',
					contactinformation: '',
					registrationtime: '',
					crossuserid: '',
					crossrefid: '',
					shhf: '',
				},

				rules: {
					registrationnumber: [
					],
					activityname: [
					],
					activitypicture: [
					],
					activitytype: [
					],
					activitytime: [
					],
					activitylocation: [
					],
					activityquota: [
						{ required: true, message: '报名人数不能为空', trigger: 'blur' },
						{ validator: validateIntNumber, trigger: 'blur' },
					],
					reasonforregistration: [
						{ required: true, message: '报名原因不能为空', trigger: 'blur' },
					],
					useraccount: [
					],
					username: [
					],
					contactinformation: [
						{ validator: validateMobile, trigger: 'blur' },
					],
					registrationtime: [
					],
					crossuserid: [
					],
					crossrefid: [
					],
					sfsh: [
					],
					shhf: [
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
			this.ruleForm.registrationtime = this.getCurDateTime()
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
						if(o=='registrationnumber'){
							this.ruleForm.registrationnumber = obj[o];
							this.ro.registrationnumber = true;
							continue;
						}
						if(o=='activityname'){
							this.ruleForm.activityname = obj[o];
							this.ro.activityname = true;
							continue;
						}
						if(o=='activitypicture'){
							this.ruleForm.activitypicture = obj[o];
							this.ro.activitypicture = true;
							continue;
						}
						if(o=='activitytype'){
							this.ruleForm.activitytype = obj[o];
							this.ro.activitytype = true;
							continue;
						}
						if(o=='activitytime'){
							this.ruleForm.activitytime = obj[o];
							this.ro.activitytime = true;
							continue;
						}
						if(o=='activitylocation'){
							this.ruleForm.activitylocation = obj[o];
							this.ro.activitylocation = true;
							continue;
						}
						if(o=='activityquota'){
							this.ruleForm.activityquota = obj[o];
							this.ro.activityquota = true;
							continue;
						}
						if(o=='reasonforregistration'){
							this.ruleForm.reasonforregistration = obj[o];
							this.ro.reasonforregistration = true;
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
						if(o=='contactinformation'){
							this.ruleForm.contactinformation = obj[o];
							this.ro.contactinformation = true;
							continue;
						}
						if(o=='registrationtime'){
							this.ruleForm.registrationtime = obj[o];
							this.ro.registrationtime = true;
							continue;
						}
						if(o=='crossuserid'){
							this.ruleForm.crossuserid = obj[o];
							this.ro.crossuserid = true;
							continue;
						}
						if(o=='crossrefid'){
							this.ruleForm.crossrefid = obj[o];
							this.ro.crossrefid = true;
							continue;
						}
					}
					this.ruleForm.activityquota = Number('1'); 				}
				// 获取用户信息
				this.$http({
					url: `${this.$storage.get('sessionTable')}/session`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						var json = data.data;
						if(this.$storage.get("role")!="管理员") {
							this.ro.activityquota = true;
						}
						if(((json.useraccount!=''&&json.useraccount) || json.useraccount==0) && this.$storage.get("role")!="管理员"){
							this.ruleForm.useraccount = json.useraccount
							this.ro.useraccount = true;
						}
						if(((json.username!=''&&json.username) || json.username==0) && this.$storage.get("role")!="管理员"){
							this.ruleForm.username = json.username
							this.ro.username = true;
						}
						if(((json.contactinformation!=''&&json.contactinformation) || json.contactinformation==0) && this.$storage.get("role")!="管理员"){
							this.ruleForm.contactinformation = json.contactinformation
							this.ro.contactinformation = true;
						}
					} else {
						this.$message.error(data.msg);
					}
				});
			
			},
			// 多级联动参数

			info(id) {
				this.$http({
					url: `activityregistration/info/${id}`,
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
					if(this.ruleForm.registrationnumber) {
						this.ruleForm.registrationnumber = String(this.ruleForm.registrationnumber)
					}
					if(this.ruleForm.activitypicture!=null) {
						this.ruleForm.activitypicture = this.ruleForm.activitypicture.replace(new RegExp(this.$base.url,"g"),"");
					}
					var objcross = this.$storage.getObj('crossObj');
					var table = this.$storage.getObj('crossTable');
					if(objcross!=null) {
						if(!this.ruleForm.activityquota){
							this.$message.error("报名人数不能为空");
							return
						}
						objcross.activityquota = parseFloat(objcross.activityquota) + parseFloat(this.ruleForm.activityquota)
											}
					//更新跨表属性
					var crossuserid;
					var crossrefid;
					var crossoptnum;
					var finishNum = 0;
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
									else {
										crossuserid=this.$storage.get('userid');
										crossrefid=obj['id'];
										crossoptnum=this.$storage.get('statusColumnName');
										crossoptnum=crossoptnum.replace(/\[/,"").replace(/\]/,"");
									}
								}
								if(crossrefid && crossuserid) {
									this.ruleForm.crossuserid = crossuserid;
									this.ruleForm.crossrefid = crossrefid;
									let params = { 
										page: 1, 
										limit: 10, 
										crossuserid:this.ruleForm.crossuserid,
										crossrefid:this.ruleForm.crossrefid,
									} 
									await this.$http({ 
										url: "activityregistration/page", 
										method: "get", 
										params: params 
									}).then(({ 
										data 
									}) => { 
										if (data && data.code === 0) {
											finishNum = data.data.total
										}
									})
								}
								if(finishNum>=crossoptnum) {
									this.$message.error(this.$storage.get('tips'));
									return false;
								}
							}
							
							await this.$http({
								url: `activityregistration/${!this.ruleForm.id ? "save" : "update"}`,
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
											this.parent.activityregistrationCrossAddOrUpdateFlag = false;
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
				this.parent.activityregistrationCrossAddOrUpdateFlag = false;
				this.parent.contentStyleChange();
			},
			activitypictureUploadChange(fileUrls) {
				this.ruleForm.activitypicture = fileUrls;
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
