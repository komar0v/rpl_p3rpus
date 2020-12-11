<%@ include file="/Views_/Panel_Member/partialHeader_Panel_Member.jsp" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Model_.M_detailBuku_Dipinjam " %>

<%    String id_buku = "", judul_buku = "", pengarang_buku = "", penerbit_buku = "", tahunterbit_buku = "", isbn_buku = "", kategori_buku = "";

    int id_pinjam = 0;
    String mulai_pinjam = "", akhir_pinjam = "", pinjam_day_remaining = "";
    String sudah_diambil = "", dikonfirmasikah = "";

    ArrayList<M_detailBuku_Dipinjam> detailBuku = (ArrayList<M_detailBuku_Dipinjam>) request.getAttribute("detailBuku_yangDipinjam");
    for (M_detailBuku_Dipinjam book_details : detailBuku) {
        id_pinjam = book_details.getId_pinjam();
        id_buku = book_details.getId_buku();
        judul_buku = book_details.getJudul_buku();
        pengarang_buku = book_details.getPengarang_buku();
        kategori_buku = book_details.getKategori_buku();
        isbn_buku = book_details.getIsbn_buku();
        tahunterbit_buku = book_details.getTahunterbit_buku();
        penerbit_buku = book_details.getPenerbit_buku();
        mulai_pinjam = book_details.getMulai_pinjam();
        akhir_pinjam = book_details.getAkhir_pinjam();
        dikonfirmasikah = book_details.getDikonfirmasikah();
        sudah_diambil = book_details.getSudah_diambil();
        pinjam_day_remaining = book_details.getPinjam_day_remaining();
    }
%>

<!-- BEGIN: Content-->
<div class="app-content content">
    <div class="content-overlay"></div>
    <div class="header-navbar-shadow"></div>
    <div class="content-wrapper">
        <div class="content-header row">
            <div class="content-header-left col-md-9 col-12 mb-2">
                <div class="row breadcrumbs-top">
                    <div class="col-12">
                        <h2 class="content-header-title float-left mb-0">Detail buku</h2>

                    </div>
                </div>
            </div>
        </div>
        <div class="content-body">
            <div class="content-body">
                <section id="multiple-column-form">
                    <div class="row match-height">
                        <div class="col-12">
                            <div class="card">

                                <div class="card-content">
                                    <div class="card-body">
                                        <div class="card-text">

                                            <div class="table-responsive">
                                                <table class="table table-hover">

                                                    <tbody>

                                                        <tr>
                                                            <th scope="row"><h3 class="card-title">Judul Buku : <% out.print(judul_buku); %></h3></th>
                                                        </tr>
                                                        <tr>
                                                            <th scope="row"><h3 class="card-title">Kategori : <% out.print(kategori_buku); %></h3></th>
                                                        </tr>
                                                        <tr>
                                                            <th scope="row"><h3 class="card-title">Pengarang : <% out.print(pengarang_buku); %></h3></th>
                                                        </tr>
                                                        <tr>
                                                            <th scope="row"><h3 class="card-title">Penerbit : <% out.print(penerbit_buku); %></h3></th>
                                                        </tr>
                                                        <tr>
                                                            <th scope="row"><h3 class="card-title">Tahun Terbit : <% out.print(tahunterbit_buku); %></h3></th>
                                                        </tr>
                                                        <tr>
                                                            <th scope="row"><h3 class="card-title">ISBN : <% out.print(isbn_buku);%></h3></th>
                                                        </tr>
                                                        <% if (dikonfirmasikah.equals("belum")) {
                                                                out.print("<td><div class=\"badge badge-pill badge-warning\">belum dikonfirmasi</div></td>");
                                                            } else {
                                                                out.print("<td><div class=\"badge badge-pill badge-success\">sudah dikonfirmasi</div></td>");
                                                            }%>

                                                        <% if (sudah_diambil.equals("belum")) {
                                                                out.print("<td><div class=\"badge badge-pill badge-warning\">Buku belum diambil</div></td>");
                                                            } else if (sudah_diambil.equals("batal")) {
                                                                out.print("<td><div class=\"badge badge-pill badge-danger\">Buku batal diambil</div></td>");
                                                            } else {
                                                                out.print("<td><strong>Mulai Pinjam : " + mulai_pinjam + "<br>Akhir Pinjam : " + akhir_pinjam + "</strong></td>");
                                                                out.print("<td><strong>Masa Berlaku : " + pinjam_day_remaining + " hari lagi</strong></td>");
                                                            }%>


                                                    </tbody>
                                                </table>
                                                <% if (sudah_diambil.equals("sudah")) { %>
                                                <form action="/member/doMember_kembalikanPinjamBuku" method="POST" accept-charset="utf-8">
                                                    <div class="col-md-8 offset-md-4">
                                                        <div class="col-md-6 col-12 mb-1">
                                                            <h5 class="text-bold-500">Pilih tanggal kebalikan buku</h5>
                                                            <input required type='text' name="tanngalKembalikanBuku" class="form-control format-picker">
                                                            <input required type="hidden" class="form-control" name="kodeBuku" value="<% out.print(id_buku);%>"><br><br>
                                                            <button type="submit" class="text-center btn btn-primary mr-1 mb-1">Kembalikan Buku</button>
                                                        </div>

                                                    </div>
                                                </form>
                                                <%}%>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>

            </div>


        </div>
    </div>
</div>
<!-- END: Content-->

<%@ include file="/Views_/Panel_Member/partialFooter_Panel_Member.jsp" %>