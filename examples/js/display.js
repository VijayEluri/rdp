dojo.require("dojo.string");
dojo.require("dojo.data.ItemFileReadStore");
dojo.require("dijit.form.Select");

dojo.ready(function(){

  display = {
  
    start: function(node, args){
      node = dojo.byId(node);
      var t = [
  '<object type="application/x-java-applet" id="display" width="1" height="1">',
    '<param name="archive" value="/applet/rdp.jar?v=' + new Date().getTime() + '" />',
    '<param name="mayscript" value="true" />',
    '<param name="code" value="net.propero.rdp.applet.RdpApplet" />',
    '<param name="port" value="${port}" />',
    '<param name="server" value="${server}" />',
    '<param name="keymap" value="${keymap}" />',
    '<param name="window_title" value="RDP Viewer" />',
    '<param name="debug_level" value="debug" />',
  '</object>'].join('');
      console.log(dojo.string.substitute(t, args));
      dojo.place(dojo.string.substitute(t, args), node);
    },
  
    destroy: function(){
      dojo.destroy('display');
    }
  };
});
