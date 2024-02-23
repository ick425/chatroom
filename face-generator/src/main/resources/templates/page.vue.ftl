<template>
    <a-card :bordered="false">
        <div class="table-page-search-wrapper">
            <!--Query Form  -->
            <a-form layout="inline">
                <a-row :gutter="40">
                    <#list table.fields as field>
                        <#if field.propertyName == "createTime">
                        <#elseif field.propertyName == "deleted">
                        <#elseif field.propertyName == "updateTime">
                        <#elseif field.propertyType == "BigDecimal">
                        <#else>
                            <a-col :md="8" :sm="24">
                                <a-form-item label="${field.comment}">
                                    <a-input v-model="queryParam.${field.propertyName}" placeholder="${field.comment}"/>
                                </a-form-item>
                            </a-col>
                        </#if>
                    </#list>
                    <a-col :md="8" :sm="24">
                        <a-form-item label="日期选择">
                            <a-date-picker
                                    v-model="startValue"
                                    :disabled-date="disabledStartDate"
                                    show-time
                                    format="YYYY-MM-DD HH:mm:ss"
                                    placeholder="开始时间"
                                    @change="changeStart"
                                    @openChange="handleStartOpenChange"
                            />
                            ~
                            <a-date-picker
                                    v-model="endValue"
                                    :disabled-date="disabledEndDate"
                                    show-time
                                    format="YYYY-MM-DD HH:mm:ss"
                                    placeholder="结束时间"
                                    :open="endOpen"
                                    @change="changeEnd"
                                    @openChange="handleEndOpenChange"
                            />
                        </a-form-item>
                    </a-col>
                    <a-col :md="8" :sm="24">
                        <a-button type="primary" @click="$refs.table.refresh(true)">查询</a-button>
                        <a-button style="margin-left: 8px" @click="resetSearchForm()">重置</a-button>
                    </a-col>
                </a-row>
            </a-form>
        </div>

        <s-table
                ref="table"
                size="small"
                rowKey="id"
                bordered
                :columns="columns"
                :data="loadData"
                :rowSelection="{ selectedRowKeys: selectedRowKeys, onChange:onSelectChange }"
        >
                <span slot="action" slot-scope="text, record">
                  <a-button type="link" @click="handleEdit(record)">编辑</a-button>
                  <a-button type="link" @click="handleDelete(record)">删除</a-button>
                </span>
        </s-table>
        <edit-modal ref="editModal" @ok="handleOk"/>
    </a-card>
</template>

<script>
    import moment from 'moment'
    import editModal from './components/editModal'
    import {STable} from '@/components'

    export default {
        name: '${entity}Page',
        components: {
            STable,
            editModal,
        },
        props: {},
        data() {
            return {
                mdl: {},
                startValue: null,
                endValue: null,
                endOpen: false,
                // 查询参数
                queryParam: {},
                // 表头
                columns: [
                    <#list table.fields as field>
                    {
                        title: '${field.comment}',
                        dataIndex: '${field.propertyName}'
                    },
                    </#list>
                    {
                        title: '操作',
                        dataIndex: 'action',
                        width: '160px',
                        scopedSlots: {customRender: 'action'}
                    }
                ],
                // 加载数据方法 必须为 Promise 对象
                loadData: parameter => {
                    return ${entity}Page({...this.queryParam}).then(
                        response => {
                            this.current = +response.data.current
                            this.size = +response.data.size
                            return response.data
                        }
                    )
                },
                selectedRowKeys: [],
                selectedRows: [],
                current: 1,
                size: 10,
            }
        },
        methods: {
            moment,
            onSelectChange(selectedRowKeys, selectedRows) {
                this.selectedRowKeys = selectedRowKeys
                this.selectedRows = selectedRows
            },
            disabledStartDate(startValue) {
                const endValue = this.endValue;
                if (!startValue || !endValue) {
                    return startValue.valueOf() > new Date().getTime()
                }
                // 如果选择了结束日期，则结束日期和开始日期之差大于30天（24*60*60*1000*30是30天的毫秒数）,还需要开始日期小于结束日期，返回true，禁止选择
                const minus = endValue.clone().subtract(30, "days");
                return startValue.valueOf() < minus.valueOf() || endValue.valueOf() <= startValue.valueOf();
            },
            disabledEndDate(endValue) {
                const startValue = this.startValue
                if (!endValue || !startValue) {
                    return false
                }
                //如果选择了开始日期，则结束日期和开始日期除了不能超过30个自然日之外，还需要结束日期不能小于开始日期，还需要不能超过今天，返回true为不能选择
                const minus = startValue.clone().subtract(-30, "days");
                return endValue.valueOf() <= startValue.valueOf() || endValue.valueOf() > moment().endOf('day') || endValue.valueOf() > minus.valueOf();
            },
            handleStartOpenChange(open) {
                if (!open) {
                    this.endOpen = true;
                }
            },
            handleEndOpenChange(open) {
                this.endOpen = open;
            },

            changeStart(e, a) {
                console.log(a)
                this.queryParam.start = a ? moment(a).format('YYYY-MM-DDTHH:mm:ss') : ''
            },

            changeEnd(e, a) {
                this.queryParam.end = a ? moment(a).format('YYYY-MM-DDTHH:mm:ss') : ''
            },

            handleEdit(record) {
                this.$refs.editModal.edit(record)
            },
            handleOk() {
                this.selectedRowKeys = []
                this.selectedRows = []
                this.$refs.table.refresh()
            },
            handleDelete(record) {
                const that = this
                this.$confirm({
                    title: '操作提醒',
                    content: '确定要删除吗?',
                    onOk() {
                        that.$notification.error({
                            message: '没有接口'
                        })
                        that.$refs.table.refresh()
                    },
                    onCancel() {
                    }
                })
            },
            resetSearchForm() {
                this.queryParam = {}
                this.startValue = null
                this.endValue = null
                this.handleOk()
            }
        }
    }
</script>
