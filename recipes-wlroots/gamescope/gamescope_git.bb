SUMMARY = "gamescope: the micro-compositor formerly known as steamcompmgr"
HOMEPAGE = "https://github.com/ValveSoftware/gamescope"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a0fe8e93fee55f3a3a8cc0681c48448a"

REQUIRED_DISTRO_FEATURES = "x11 wayland"

DEPENDS += " \
	aom \
	glm \
	glslang-native \
	hwdata \
	libei \
	libdecor \
	libdisplay-info \
	libinput \
	libx11\
	libxcomposite \
	libxcursor \
	libxkbcommon \
	libxres \
	libxmu \
	libxrender \
	libxtst \
	seatd \
	stb \
	vulkan-headers \
	wayland \
	wayland-native \
	wayland-protocols \
	xcb-util-wm \
	xwayland \
"

RDEPENDS:${PN} = "xwayland hwdata"

CXXFLAGS:append = " -I${STAGING_INCDIR}/stb"

PACKAGECONFIG ?= "${@bb.utils.filter('DISTRO_FEATURES', 'pipewire', d)} rt_cap avif_screenshots openvr input_emulation drm_backend sdl2_backend"
PACKAGECONFIG[benchmark] = "-Dbenchmark=enabled,-Dbenchmark=disabled"
PACKAGECONFIG[input_emulation] = "-Dinput_emulation=enabled,-Dinput_emulation=disabled,libei"
PACKAGECONFIG[openvr] = "-Denable_openvr_support=true,-Denable_openvr_support=false"
PACKAGECONFIG[pipewire] = "-Dpipewire=enabled,-Dpipewire=disabled,pipewire"
PACKAGECONFIG[avif_screenshots] = "-Davif_screenshots=enabled,-Davif_screenshots=disabled,libavif"
PACKAGECONFIG[rt_cap] = "-Drt_cap=enabled,-Drt_cap=disabled,libcap"
PACKAGECONFIG[sdl2_backend] = "-Dsdl2_backend=enabled,-Dsdl2_backend=disabled,libsdl2"
PACKAGECONFIG[drm_backend] = "-Ddrm_backend=enabled,-Ddrm_backend=disabled,libdrm"

SRC_URI = "   \
	gitsm://github.com/ValveSoftware/gamescope.git;branch=jupiter-3.6;protocol=https \
	file://0001-meson.build-dont-fail-if-stb-is-not-found.patch \
	file://0001-meson.build-dont-leak-buildpath.patch \
"
SRCREV = "97a2e038105e43fc7b4c78585eefc639ad95319f"
PV = "3.14.15"

S = "${WORKDIR}/git"

inherit meson pkgconfig features_check

EXTRA_OEMESON += "--buildtype release"

FILES:${PN} += "${datadir} ${libdir}"
FILES:${PN}-dev = "${includedir} ${libdir}/pkgconfig"
