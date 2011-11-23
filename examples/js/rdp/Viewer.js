dojo.provide('rdp.Viewer');

dojo.require('java.applet');

dojo.declare('rdp.Viewer', null, {

    started: false,
    title: null,
    host: null,
    port: null,
    node: null,
    archive: dojo.moduleUrl("rdp","resources/rdp.jar"),

    constructor: function(args){
        dojo.safeMixin(this, args);
        
        this.id = 'rdp-viewer-' + rdp.Viewer.id;
        rdp.Viewer.id = rdp.Viewer.id + 1;
        if(typeof rdp.Viewer.viewers[this.id] !== 'undefined'){
            console.log('rdp.Viewer already present');
            dojo.destroy(this.id);
        }
        rdp.Viewer.viewers[this.id] = this;
    },

    start: function(){
        if(this.started){
            console.log('error already started');
            return;
        }

        applet.inject(this.node, {
          archive: this.archive.uri + '?v=' + new Date().getTime(),
          code: "net.propero.rdp.applet.RdpApplet",
          port: this.port,
          host: this.host,
          title: this.title,
          log_level: "debug"
        });
    },

    onDestroy: function(){
        var self = this;
        function doIt(){
            dojo.destroy(this.id);
        }
        setTimeout(doIt, 0);
    }
});

rdp.Viewer.viewers = {};
rdp.Viewer.id = 0;
