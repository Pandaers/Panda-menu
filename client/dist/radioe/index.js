const prefixCls = 'i-radio';

Component({
    externalClasses: ['i-class'],
    relations: {
        '../radio-group/index': {
            type: 'parent'
        }
    },
    properties: {
        value: {
            type: String,
            value: ''
        },
        index:{
          type: Number,
          value:0
        },
      preindex: {
        type: Number,
        value: 0
      },
        checked: {
            type: Boolean,
            value: false
        },
        disabled: {
            type: Boolean,
            value: false
        },
        color: {
            type: String,
            value: '#2d8cf0'
        },
      options: {
        type: String,
        value: '选项'
      },
        position: {
            type: String,
            value: 'left', //left right
            observer: 'setPosition'
        }
    },
    data: {
        checked: true,
        positionCls: `${prefixCls}-radio-left`,
    },
    attached() {
        this.setPosition();
    },
    methods: {
        changeCurrent(current) {
            this.setData({ checked: current });
        },
        radioChange() {
            if (this.data.disabled) return;
            const item = { current: !this.data.checked, value: this.data.value };
            const parent = this.getRelationNodes('../radio-group/index')[0];
            parent ? parent.emitEvent(item) : this.triggerEvent('change', item);
        },
      handleTap() {

        const item = { preindex: this.data.preindex, index: this.data.index };
        this.triggerEvent('click',item);
  
      },
      onchange(e){
        console.log(e)
        const item = { value: e.detail.detail.value,index:this.data.index,preindex:this.data.preindex} 
        this.triggerEvent('input', item);
      },
        setPosition() {
            this.setData({
                positionCls: this.data.position.indexOf('left') !== -1 ? `${prefixCls}-radio-left` : `${prefixCls}-radio-right`,
            });
        }
    }
});
