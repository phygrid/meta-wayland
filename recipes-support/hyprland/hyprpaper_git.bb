SUMMARY = "Hyprpaper is a blazing fast wallpaper utility for Hyprland."
HOMEPAGE = "https://hyprland.org/"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=936078e4e67b0e1e1bd1e862d4ffbc25"

SRC_URI = "git://github.com/hyprwm/hyprpaper.git;protocol=https;branch=main"

PV = "0.7.0"
SRCREV = "03cd362f494d6771b4f524f4fafbc28bade00c8e"
S = "${WORKDIR}/git"

DEPENDS += " \
	cairo \
	file \
	fribidi \
	hyprlang \
	jpeg \
	libwebp \
	libxdmcp \
	pango \
	pcre \
	pcre2 \
	util-linux \
	wayland \
	wayland-native \
	wayland-protocols \
	wlroots \
"

RDEPENDS:${PN} = "hyprlang"

REQUIRED_DISTRO_FEATURES = "wayland"

EXTRA_OEMAKE = " \
	WAYLAND_SCANNER=${STAGING_BINDIR_NATIVE}/wayland-scanner \
	WAYLAND_PROTOCOLS=${STAGING_DATADIR}/wayland-protocols \
"

do_configure:prepend() {
    sed -i -e '$ainstall(TARGETS hyprpaper)' ${S}/CMakeLists.txt
}

do_compile:prepend() {
    cd ${S} && oe_runmake protocols
}

do_install() {
    install -d ${D}${bindir}
    install -m0755 ${B}/hyprpaper ${D}${bindir}
}

inherit cmake pkgconfig features_check
