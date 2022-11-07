const main = {
  init: function() {
    const _this = this;
    $('#btn-remove').on('click', function() {
      _this.delete();
    })
  },

  delete: function() {
    const _this = this;
    const base_request_url = _ctx + '/myBoard';
    const id = $('#btn-remove').val();
    const fileUrls = _this.fileSrcget;

    $.ajax({
      type: 'DELETE',
      url: base_request_url + '/' + id,
      headers: {
        'X-CSRF-TOKEN': token
      },
      data: {'fileUrls': fileUrls},
      async: false
    }).done(function() {
      window.location.href = base_request_url;
    }).fail(function(error) {
      console.log('[ Error Response ]\n' + error)
    });
  },

  fileSrcget: function() {
    let imgs = document.getElementsByTagName("img");
    let imgSrcs = [];

    for (let i = 0; i < imgs.length; i++) {
      imgSrcs.push(decodeURI(imgs[i].src));
    }

    return imgSrcs;
  }
};

main.init();