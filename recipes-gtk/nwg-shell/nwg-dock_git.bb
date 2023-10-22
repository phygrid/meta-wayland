SUMMARY = "GTK3-based dock for wlroots based compositors."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=fffa55ad1c828db5e334374fb1182530"

DEPENDS = " \
	gtk+3 \
	gtk-layer-shell \
"
RDEPENDS:${PN} = "go-runtime"

GO_IMPORT = "github.com/nwg-piotr/nwg-dock.git"

SRC_URI = "git://${GO_IMPORT};nobranch=1;protocol=https"

PV = "0.3.9"
SRCREV = "6fdd055eaff282298ea5496d203514b5f99314a0"

inherit go go-mod pkgconfig

GO_INSTALL = "${GO_IMPORT}"

do_install:append() {
	# remove precompiled x86 binaries
	rm -rf ${D}${libdir}/go/src/${GO_IMPORT}/bin
	rm -rf ${D}${libdir}/go/pkg/mod/github.com/dlasky/gotk3-layershell*/example
}

do_compile[network] = "1"

PRIVATE_LIBS:${PN}:append = " libstd.so"
