<template>
    <a-modal
            :title="modalTitle"
            :width="800"
            :visible="visible"
            :confirmLoading="confirmLoading"
            @ok="handleSubmit"
            @cancel="handleCancel"
    >
        <a-spin :spinning="confirmLoading">
            <a-form :form="form">
                <a-form-item label="id" :labelCol="labelCol" :wrapperCol="wrapperCol" v-show="false">
                    <a-input v-if="modalOpera==1" v-decorator="['id', {rules: [{required: true}]}]"/>
                </a-form-item>
                <#list table.fields as field>
                    <#if field.propertyName == "id">
                    <#elseif field.propertyName == "createTime">
                    <#elseif field.propertyName == "updateTime">
                    <#elseif field.propertyName == "deleted">
                    <#else>
                        <a-form-item label="${field.comment}" :labelCol="labelCol" :wrapperCol="wrapperCol">
                            <a-input
                                    v-decorator="['${field.propertyName}', {rules: [{required: true, message: '请输入${field.comment}'}]}]"
                            />
                        </a-form-item>
                    </#if>
                </#list>
            </a-form>
        </a-spin>
    </a-modal>
</template>

<script>
    import store from '@/store'
    import {add$

    {
        entity
    }
    ,
    edit$
    {
        entity
    }
    }
    from
    '@/api/'

    export default {
        data() {
            return {
                labelCol: {
                    xs: {span: 24},
                    sm: {span: 7}
                },
                wrapperCol: {
                    xs: {span: 24},
                    sm: {span: 13}
                },
                modalTitle: '',
                modalOpera: 0, // 0：新增 1：编辑
                visible: false,
                confirmLoading: false,
                form: this.$form.createForm(this),
                currentRow: {},
                headers: {
                    Authorization: store.getters.token
                },
                loading: false,
            }
        },
        created() {
        },
        methods: {
            add() {
                this.modal('新建', 0)
                setTimeout(() => {
                }, 100)
            },
            edit(record) {
                this.modal('编辑', 1)
                setTimeout(() => {
                    this.currentRow = record
                    this.form.setFieldsValue(this.currentRow)
                }, 500)
            },
            modal(title, opera) {
                this.modalTitle = title
                this.modalOpera = opera
                this.visible = true
                this.form.resetFields()
            },
            handleSubmit() {
                const {
                    form: {validateFields}
                } = this
                var that = this
                this.confirmLoading = true
                validateFields((errors, values) => {
                    if (!errors) {
                        console.log(values)
                        if (this.modalOpera === 0) {
                            add${entity}(values).then(res => {
                                that.$notification.success({
                                    message: '操作成功！'
                                })
                                that.$emit('ok')
                                that.handleCancel()
                            })
                        } else if (this.modalOpera === 1) {
                            edit${entity}(values).then(res => {
                                that.$notification.success({
                                    message: '操作成功！'
                                })
                                that.$emit('ok')
                                that.handleCancel()
                            })
                        }
                    }
                    this.confirmLoading = false
                })
            },
            handleCancel() {
                this.visible = false
                this.confirmLoading = false
            }
        }
    }
</script>
