
<%@ include file="/Views_/Panel_Admin/partialHeader_Panel_Admin.jsp" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Model_.M_pinjamBuku" %>

<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1>Konfirmasi Peminjaman</h1>
                </div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item active">Dashboard</li>
                        <li class="breadcrumb-item active">Konfirmasi Pinjam</li>
                    </ol>
                </div>
            </div>
        </div><!-- /.container-fluid -->
    </section>

    <!-- Main content -->
    <section class="content">
        <div class="container-fluid">
            <div class="row">
                <div class="col-12">

                    <div class="card">

                        <!-- /.card-header -->
                        <div class="card-body">
                            <table id="dataTable_" class="table table-bordered table-striped">
                                <thead>
                                    <tr>
                                        <th>ID Pinjam</th>
                                        <th>Nama Member</th>
                                        <th>Buku dipinjam</th>
                                        <th>Tanggal ambil buku</th>
                                        <th>Tindakan</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% ArrayList<M_pinjamBuku> listMembers = (ArrayList<M_pinjamBuku>) request.getAttribute("list_members");
                                        for (M_akunMember members_ : listMembers) {
                                    %>
                                    <tr>
                                        <td><%= members_.getNama_member()%></td>
                                        <td>
                                            <button type="button" data-toggle="modal" data-target="#modal-sm2" class="btn btn-danger btn-sm"><i class="fas fa-trash-alt"></i> Hapus Member</button>
                                            <div class="modal fade" id="modal-sm2">
                                                <div class="modal-dialog modal-sm">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h4 class="modal-title">Konfirmasi Hapus</h4>

                                                        </div>
                                                        <div class="modal-body">
                                                            <div class="alert alert-warning alert-dismissible">
                                                                <h6><i class="icon fas fa-exclamation-triangle"></i>Perhatian!</h6>

                                                                <p>Semua data member "<%= members_.getNama_member()%>" akan hilang, lanjutkan?<br></p>

                                                            </div>
                                                        </div>
                                                        <div class="modal-footer justify-content-between">
                                                            <button type="button" class="btn btn-block btn-default" data-dismiss="modal">Tidak, batalkan hapus</button>
                                                            <button onclick="window.location.href = '<%=request.getContextPath()%>/admin/doAdmin_deleteMember?idMember_=<%= members_.getId_member()%>'" type="button" class="btn btn-block btn-primary">Ya, lanjut hapus</button>
                                                        </div>
                                                    </div>
                                                    <!-- /.modal-content -->
                                                </div>
                                                <!-- /.modal-dialog -->
                                            </div>
                                        </td>
                                    </tr>
                                    <%}%> 
                                </tbody>
                            </table>
                        </div>
                        <!-- /.card-body -->
                    </div>
                    <!-- /.card -->
                </div>
                <!-- /.col -->
            </div>
            <!-- /.row -->
        </div>
        <!-- /.container-fluid -->
    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->

<%@ include file="/Views_/Panel_Admin/partialFooter_Panel_Admin.jsp" %>
