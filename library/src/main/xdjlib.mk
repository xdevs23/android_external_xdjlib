# Copyright (C) 2016 Simao Gomes Viana
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

# Define the local path of this project
LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_STATIC_JAVA_LIBRARIES := \
    android-support-v7-appcompat \
    android-support-v4

LOCAL_RESOURCE_DIR := \
    $(LOCAL_PATH)/res

LOCAL_JAVA_LIBRARIES := android-support-annotations

# Define the module name and required SDK version
LOCAL_MODULE := xdjlib
LOCAL_SDK_VERSION := 23
LOCAL_SRC_FILES := $(call all-java-files-under, src)

# Yes, this is a static java library
include $(BUILD_STATIC_JAVA_LIBRARY)
