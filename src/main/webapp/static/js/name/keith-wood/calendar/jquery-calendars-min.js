﻿/* http://keith-wood.name/calendars.html
   Calendars for jQuery v1.1.4.
   Written by Keith Wood (kbwood{at}iinet.com.au) August 2009.
   Dual licensed under the GPL (http://dev.jquery.com/browser/trunk/jquery/GPL-LICENSE.txt) and 
   MIT (http://dev.jquery.com/browser/trunk/jquery/MIT-LICENSE.txt) licenses. 
   Please attribute the author if you use it. */
(function($){
    function Calendars(){
        this.regional={
            '':{
                invalidCalendar:'Calendar {0} not found',
                invalidDate:'Invalid {0} date',
                invalidMonth:'Invalid {0} month',
                invalidYear:'Invalid {0} year',
                differentCalendars:'Cannot mix {0} and {1} dates'
            }
        };
        
    this.local=this.regional[''];
    this.calendars={};
    
    this._localCals={}
}
$.extend(Calendars.prototype,{
    instance:function(a,b){
        a=(a||'gregorian').toLowerCase();
        b=b||'';
        var c=this._localCals[a+'-'+b];
        if(!c&&this.calendars[a]){
            c=new this.calendars[a](b);
            this._localCals[a+'-'+b]=c
            }
            if(!c){
            throw(this.local.invalidCalendar||this.regional[''].invalidCalendar).replace(/\{0\}/,a)
            }
            return c
        },
    newDate:function(a,b,c,d,e){
        d=(a!=null&&a.year?a.calendar():(typeof d=='string'?this.instance(d,e):d))||this.instance();
        return d.newDate(a,b,c)
        }
    });
function CDate(a,b,c,d){
    this._calendar=a;
    this._year=b;
    this._month=c;
    this._day=d;
    if(this._calendar._validateLevel==0&&!this._calendar.isValid(this._year,this._month,this._day)){
        throw($.calendars.local.invalidDate||$.calendars.regional[''].invalidDate).replace(/\{0\}/,this._calendar.local.name)
        }
    }
function pad(a,b){
    a=''+a;
    return'000000'.substring(0,b-a.length)+a
    }
    $.extend(CDate.prototype,{
    newDate:function(a,b,c){
        return this._calendar.newDate((a==null?this:a),b,c)
        },
    year:function(a){
        return(arguments.length==0?this._year:this.set(a,'y'))
        },
    month:function(a){
        return(arguments.length==0?this._month:this.set(a,'m'))
        },
    day:function(a){
        return(arguments.length==0?this._day:this.set(a,'d'))
        },
    date:function(a,b,c){
        if(!this._calendar.isValid(a,b,c)){
            throw($.calendars.local.invalidDate||$.calendars.regional[''].invalidDate).replace(/\{0\}/,this._calendar.local.name)
            }
            this._year=a;
        this._month=b;
        this._day=c;
        return this
        },
    leapYear:function(){
        return this._calendar.leapYear(this)
        },
    epoch:function(){
        return this._calendar.epoch(this)
        },
    formatYear:function(){
        return this._calendar.formatYear(this)
        },
    monthOfYear:function(){
        return this._calendar.monthOfYear(this)
        },
    weekOfYear:function(){
        return this._calendar.weekOfYear(this)
        },
    daysInYear:function(){
        return this._calendar.daysInYear(this)
        },
    dayOfYear:function(){
        return this._calendar.dayOfYear(this)
        },
    daysInMonth:function(){
        return this._calendar.daysInMonth(this)
        },
    dayOfWeek:function(){
        return this._calendar.dayOfWeek(this)
        },
    weekDay:function(){
        return this._calendar.weekDay(this)
        },
    extraInfo:function(){
        return this._calendar.extraInfo(this)
        },
    add:function(a,b){
        return this._calendar.add(this,a,b)
        },
    set:function(a,b){
        return this._calendar.set(this,a,b)
        },
    compareTo:function(a){
        if(this._calendar.name!=a._calendar.name){
            throw($.calendars.local.differentCalendars||$.calendars.regional[''].differentCalendars).replace(/\{0\}/,this._calendar.local.name).replace(/\{1\}/,a._calendar.local.name)
            }
            var c=(this._year!=a._year?this._year-a._year:this._month!=a._month?this.monthOfYear()-a.monthOfYear():this._day-a._day);
        return(c==0?0:(c<0?-1:+1))
        },
    calendar:function(){
        return this._calendar
        },
    toJD:function(){
        return this._calendar.toJD(this)
        },
    fromJD:function(a){
        return this._calendar.fromJD(a)
        },
    toJSDate:function(){
        return this._calendar.toJSDate(this)
        },
    fromJSDate:function(a){
        return this._calendar.fromJSDate(a)
        },
    toString:function(){
        return(this.year()<0?'-':'')+pad(Math.abs(this.year()),4)+'-'+pad(this.month(),2)+'-'+pad(this.day(),2)
        }
    });
function BaseCalendar(){
    this.shortYearCutoff='+10'
    }
    $.extend(BaseCalendar.prototype,{
    _validateLevel:0,
    newDate:function(a,b,c){
        if(a==null){
            return this.today()
            }
            if(a.year){
            this._validate(a,b,c,$.calendars.local.invalidDate||$.calendars.regional[''].invalidDate);
            c=a.day();
            b=a.month();
            a=a.year()
            }
            return new CDate(this,a,b,c)
        },
    today:function(){
        return this.fromJSDate(new Date())
        },
    epoch:function(a){
        var b=this._validate(a,this.minMonth,this.minDay,$.calendars.local.invalidYear||$.calendars.regional[''].invalidYear);
        return(b.year()<0?this.local.epochs[0]:this.local.epochs[1])
        },
    formatYear:function(a){
        var b=this._validate(a,this.minMonth,this.minDay,$.calendars.local.invalidYear||$.calendars.regional[''].invalidYear);
        return(b.year()<0?'-':'')+pad(Math.abs(b.year()),4)
        },
    monthsInYear:function(a){
        this._validate(a,this.minMonth,this.minDay,$.calendars.local.invalidYear||$.calendars.regional[''].invalidYear);
        return 12
        },
    monthOfYear:function(a,b){
        var c=this._validate(a,b,this.minDay,$.calendars.local.invalidMonth||$.calendars.regional[''].invalidMonth);
        return(c.month()+this.monthsInYear(c)-this.firstMonth)%this.monthsInYear(c)+this.minMonth
        },
    fromMonthOfYear:function(a,b){
        var m=(b+this.firstMonth-2*this.minMonth)%this.monthsInYear(a)+this.minMonth;
        this._validate(a,m,this.minDay,$.calendars.local.invalidMonth||$.calendars.regional[''].invalidMonth);
        return m
        },
    daysInYear:function(a){
        var b=this._validate(a,this.minMonth,this.minDay,$.calendars.local.invalidYear||$.calendars.regional[''].invalidYear);
        return(this.leapYear(b)?366:365)
        },
    dayOfYear:function(a,b,c){
        var d=this._validate(a,b,c,$.calendars.local.invalidDate||$.calendars.regional[''].invalidDate);
        return d.toJD()-this.newDate(d.year(),this.fromMonthOfYear(d.year(),this.minMonth),this.minDay).toJD()+1
        },
    daysInWeek:function(){
        return 7
        },
    dayOfWeek:function(a,b,c){
        var d=this._validate(a,b,c,$.calendars.local.invalidDate||$.calendars.regional[''].invalidDate);
        return(Math.floor(this.toJD(d))+2)%this.daysInWeek()
        },
    extraInfo:function(a,b,c){
        this._validate(a,b,c,$.calendars.local.invalidDate||$.calendars.regional[''].invalidDate);
        return{}
    },
add:function(a,b,c){
    this._validate(a,this.minMonth,this.minDay,$.calendars.local.invalidDate||$.calendars.regional[''].invalidDate);
    return this._correctAdd(a,this._add(a,b,c),b,c)
    },
_add:function(c,f,g){
    this._validateLevel++;
    if(g=='d'||g=='w'){
        var h=c.toJD()+f*(g=='w'?this.daysInWeek():1);
        var d=c.calendar().fromJD(h);
        this._validateLevel--;
        return[d.year(),d.month(),d.day()]
        }
        try{
        var y=c.year()+(g=='y'?f:0);
        var m=c.monthOfYear()+(g=='m'?f:0);
        var d=c.day();
        var i=function(a){
            while(m<a.minMonth){
                y--;
                m+=a.monthsInYear(y)
                }
                var b=a.monthsInYear(y);
            while(m>b-1+a.minMonth){
                y++;
                m-=b;
                b=a.monthsInYear(y)
                }
            };
        
    if(g=='y'){
        if(c.month()!=this.fromMonthOfYear(y,m)){
            m=this.newDate(y,c.month(),this.minDay).monthOfYear()
            }
            m=Math.min(m,this.monthsInYear(y));
        d=Math.min(d,this.daysInMonth(y,this.fromMonthOfYear(y,m)))
        }else if(g=='m'){
        i(this);
        d=Math.min(d,this.daysInMonth(y,this.fromMonthOfYear(y,m)))
        }
        var j=[y,this.fromMonthOfYear(y,m),d];
    this._validateLevel--;
    return j
    }catch(e){
    this._validateLevel--;
    throw e;
}
},
_correctAdd:function(a,b,c,d){
    if(!this.hasYearZero&&(d=='y'||d=='m')){
        if(b[0]==0||(a.year()>0)!=(b[0]>0)){
            var e={
                y:[1,1,'y'],
                m:[1,this.monthsInYear(-1),'m'],
                w:[this.daysInWeek(),this.daysInYear(-1),'d'],
                d:[1,this.daysInYear(-1),'d']
                }
                [d];
            var f=(c<0?-1:+1);
            b=this._add(a,c*e[0]+f*e[1],e[2])
            }
        }
    return a.date(b[0],b[1],b[2])
},
set:function(a,b,c){
    this._validate(a,this.minMonth,this.minDay,$.calendars.local.invalidDate||$.calendars.regional[''].invalidDate);
    var y=(c=='y'?b:a.year());
    var m=(c=='m'?b:a.month());
    var d=(c=='d'?b:a.day());
    if(c=='y'||c=='m'){
        d=Math.min(d,this.daysInMonth(y,m))
        }
        return a.date(y,m,d)
    },
isValid:function(a,b,c){
    this._validateLevel++;
    var d=(this.hasYearZero||a!=0);
    if(d){
        var e=this.newDate(a,b,this.minDay);
        d=(b>=this.minMonth&&b-this.minMonth<this.monthsInYear(e))&&(c>=this.minDay&&c-this.minDay<this.daysInMonth(e))
        }
        this._validateLevel--;
    return d
    },
toJSDate:function(a,b,c){
    var d=this._validate(a,b,c,$.calendars.local.invalidDate||$.calendars.regional[''].invalidDate);
    return $.calendars.instance().fromJD(this.toJD(d)).toJSDate()
    },
fromJSDate:function(a){
    return this.fromJD($.calendars.instance().fromJSDate(a).toJD())
    },
_validate:function(a,b,c,d){
    if(a.year){
        if(this._validateLevel==0&&this.name!=a.calendar().name){
            throw($.calendars.local.differentCalendars||$.calendars.regional[''].differentCalendars).replace(/\{0\}/,this.local.name).replace(/\{1\}/,a.calendar().local.name)
            }
            return a
        }
        try{
        this._validateLevel++;
        if(this._validateLevel==1&&!this.isValid(a,b,c)){
            throw d.replace(/\{0\}/,this.local.name)
            }
            var f=this.newDate(a,b,c);
        this._validateLevel--;
        return f
        }catch(e){
        this._validateLevel--;
        throw e;
    }
}
});
function GregorianCalendar(a){
    this.local=this.regional[a||'']||this.regional['']
    }
    GregorianCalendar.prototype=new BaseCalendar;
$.extend(GregorianCalendar.prototype,{
    name:'Gregorian',
    jdEpoch:1721425.5,
    daysPerMonth:[31,28,31,30,31,30,31,31,30,31,30,31],
    hasYearZero:false,
    minMonth:1,
    firstMonth:1,
    minDay:1,
    regional:{
        '':{
            name:'Gregorian',
            epochs:['BCE','CE'],
            monthNames:['January','February','March','April','May','June','July','August','September','October','November','December'],
            monthNamesShort:['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'],
            dayNames:['Sunday','Monday','Tuesday','Wednesday','Thursday','Friday','Saturday'],
            dayNamesShort:['Sun','Mon','Tue','Wed','Thu','Fri','Sat'],
            dayNamesMin:['Su','Mo','Tu','We','Th','Fr','Sa'],
            dateFormat:'mm/dd/yyyy',
            firstDay:0,
            isRTL:false
        }
    },
leapYear:function(a){
    var b=this._validate(a,this.minMonth,this.minDay,$.calendars.local.invalidYear||$.calendars.regional[''].invalidYear);
    var a=b.year()+(b.year()<0?1:0);
    return a%4==0&&(a%100!=0||a%400==0)
    },
weekOfYear:function(a,b,c){
    var d=this.newDate(a,b,c);
    d.add(4-(d.dayOfWeek()||7),'d');
    return Math.floor((d.dayOfYear()-1)/7)+1
    },
daysInMonth:function(a,b){
    var c=this._validate(a,b,this.minDay,$.calendars.local.invalidMonth||$.calendars.regional[''].invalidMonth);
    return this.daysPerMonth[c.month()-1]+(c.month()==2&&this.leapYear(c.year())?1:0)
    },
weekDay:function(a,b,c){
    return(this.dayOfWeek(a,b,c)||7)<6
    },
toJD:function(c,d,e){
    var f=this._validate(c,d,e,$.calendars.local.invalidDate||$.calendars.regional[''].invalidDate);
    c=f.year();
    d=f.month();
    e=f.day();
    if(c<0){
        c++
    }
    if(d<3){
        d+=12;
        c--
    }
    var a=Math.floor(c/100);
    var b=2-a+Math.floor(a/4);
    return Math.floor(365.25*(c+4716))+Math.floor(30.6001*(d+1))+e+b-1524.5
    },
fromJD:function(f){
    var z=Math.floor(f+0.5);
    var a=Math.floor((z-1867216.25)/36524.25);
    a=z+1+a-Math.floor(a/4);
    var b=a+1524;
    var c=Math.floor((b-122.1)/365.25);
    var d=Math.floor(365.25*c);
    var e=Math.floor((b-d)/30.6001);
    var g=b-d-Math.floor(e*30.6001);
    var h=e-(e>13.5?13:1);
    var i=c-(h>2.5?4716:4715);
    if(i<=0){
        i--
    }
    return this.newDate(i,h,g)
    },
toJSDate:function(a,b,c){
    var d=this._validate(a,b,c,$.calendars.local.invalidDate||$.calendars.regional[''].invalidDate);
    var e=new Date(d.year(),d.month()-1,d.day());
    e.setHours(0);
    e.setMinutes(0);
    e.setSeconds(0);
    e.setMilliseconds(0);
    e.setHours(e.getHours()>12?e.getHours()+2:0);
    return e
    },
fromJSDate:function(a){
    return this.newDate(a.getFullYear(),a.getMonth()+1,a.getDate())
    }
});
$.calendars=new Calendars();
$.calendars.cdate=CDate;
$.calendars.baseCalendar=BaseCalendar;
$.calendars.calendars.gregorian=GregorianCalendar
})(jQuery);