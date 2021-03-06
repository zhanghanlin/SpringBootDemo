$(function () {
    var roleTable = {
        table: function () {
            $('#list').DataTable({
                bPaginate: true,// 分页按钮
                lengthChange: false,  //修改每页数量
                searching: false,  //搜索功能
                order: [[0, 'DESC']],   //默认排序字段
                info: true,   //显示数据信息 第几页,总共几页等等
                autoWidth: false, //是否固定宽度
                bProcessing: false,    //服务器等等提示
                bServerSide: true, //表示从服务器获取
                lengthMenu: [[10, 15, 25, 50], [10, 15, 25, 50]],    //每页数据管理
                bDestroy: true,
                bSortCellsTop: true,
                bSortClasses: true,
                ajax: {
                    url: '/role/api/list'
                },
                columns: [
                    {data: 'name'},
                    {data: 'note'},
                    {data: 'uniqueKey'},
                    {data: 'status'},
                ],
                select: true,
                language: {
                    url: '/js/bootstrap/dataTable.oLanguage.json'
                },
                fnRowCallback: function (nRow, aData, iDisplayIndex, iDisplayIndexFull) {
                    if ($('#action').length > 0) {
                        $(nRow).append('<td>' +
                            '<a href="/role/assignInfo/' + aData["id"] + '">查看</a>&nbsp;&nbsp;' +
                            '<a href="/role/edit/' + aData["id"] + '">编辑</a></td>')
                    }
                },
                fnInitComplete: function () {
                    if ($('#addRole').length > 0) return;
                    $('#list_wrapper .row:first div:first').append('<a href="/role/edit/0" id="addRole" type="button" class="btn btn-default">新增角色</a>')
                }
            });
        }
    };
    roleTable.table();
});