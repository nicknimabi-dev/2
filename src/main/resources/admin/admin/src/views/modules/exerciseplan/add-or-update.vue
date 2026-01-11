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
				<el-form-item class="input" v-if="type!='info'"  label="计划用时" prop="plannedtime" >
					<el-input v-model="ruleForm.plannedtime" placeholder="计划用时" clearable  :readonly="ro.plannedtime"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="计划用时" prop="plannedtime" >
					<el-input v-model="ruleForm.plannedtime" placeholder="计划用时" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="打卡次数" prop="checkintimes" >
					<el-input v-model.number="ruleForm.checkintimes" placeholder="打卡次数" clearable  :readonly="ro.checkintimes"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="打卡次数" prop="checkintimes" >
					<el-input v-model="ruleForm.checkintimes" placeholder="打卡次数" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="运动目标" prop="exercisegoal" >
					<el-input v-model="ruleForm.exercisegoal" placeholder="运动目标" clearable  :readonly="ro.exercisegoal"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="运动目标" prop="exercisegoal" >
					<el-input v-model="ruleForm.exercisegoal" placeholder="运动目标" readonly></el-input>
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
				<el-form-item class="date" v-if="type!='info'" label="计划时间" prop="time" >
					<el-date-picker
						value-format="yyyy-MM-dd HH:mm:ss"
						v-model="ruleForm.time" 
						type="datetime"
						:readonly="ro.time"
						placeholder="计划时间"
					></el-date-picker>
				</el-form-item>
				<el-form-item class="input" v-else-if="ruleForm.time" label="计划时间" prop="time" >
					<el-input v-model="ruleForm.time" placeholder="计划时间" readonly></el-input>
				</el-form-item>
			</template>
			<el-form-item v-if="type!='info'"  label="计划详情" prop="plandetails" >
				<editor 
					style="min-width: 200px; max-width: 600px;"
					v-model="ruleForm.plandetails" 
					class="editor"
					myQuillEditor="plandetails"
					action="file/upload">
				</editor>
			</el-form-item>
			<el-form-item v-else-if="ruleForm.plandetails" label="计划详情" prop="plandetails" >
				<span class="text ql-snow ql-editor" v-html="ruleForm.plandetails"></span>
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
    

	</div>
</template>
<script>
	import { 
		isIntNumer,
	} from "@/utils/validate";
	export default {
		data() {
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
					plannedtime : false,
					checkintimes : false,
					exercisegoal : false,
					plandetails : false,
					useraccount : false,
					username : false,
					headportrait : false,
					time : false,
				},
			
				ruleForm: {
					plantitle: '',
					plannedtime: '',
					checkintimes: Number('0'),
					exercisegoal: '',
					plandetails: '',
					useraccount: '',
					username: '',
					headportrait: '',
					time: '',
				},

				rules: {
					plantitle: [
						{ required: true, message: '计划标题不能为空', trigger: 'blur' },
					],
					plannedtime: [
					],
					checkintimes: [
						{ validator: validateIntNumber, trigger: 'blur' },
					],
					exercisegoal: [
						{ required: true, message: '运动目标不能为空', trigger: 'blur' },
					],
					plandetails: [
					],
					useraccount: [
					],
					username: [
					],
					headportrait: [
					],
					time: [
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
			this.ruleForm.time = this.getCurDateTime()
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
						if(o=='plantitle'){
							this.ruleForm.plantitle = obj[o];
							this.ro.plantitle = true;
							continue;
						}
						if(o=='plannedtime'){
							this.ruleForm.plannedtime = obj[o];
							this.ro.plannedtime = true;
							continue;
						}
						if(o=='checkintimes'){
							this.ruleForm.checkintimes = obj[o];
							this.ro.checkintimes = true;
							continue;
						}
						if(o=='exercisegoal'){
							this.ruleForm.exercisegoal = obj[o];
							this.ro.exercisegoal = true;
							continue;
						}
						if(o=='plandetails'){
							this.ruleForm.plandetails = obj[o];
							this.ro.plandetails = true;
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
						if(o=='time'){
							this.ruleForm.time = obj[o];
							this.ro.time = true;
							continue;
						}
					}
					this.ruleForm.checkintimes = Number('0'); 				}
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
					url: `exerciseplan/info/${id}`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						this.ruleForm = data.data;
						//解决前台上传图片后台不显示的问题
						let reg=new RegExp('../../../upload','g')//g代表全部
						this.ruleForm.plandetails = this.ruleForm.plandetails.replace(reg,'../../../springbootdti6c7gx/upload');
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
								url: `exerciseplan/${!this.ruleForm.id ? "save" : "update"}`,
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
											this.parent.exerciseplanCrossAddOrUpdateFlag = false;
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
				this.parent.exerciseplanCrossAddOrUpdateFlag = false;
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
