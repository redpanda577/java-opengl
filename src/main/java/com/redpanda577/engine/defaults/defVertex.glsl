#version 410

layout(location = 0) in vec3 position;
layout(location = 1) in vec2 uv;
layout(location = 2) in vec3 normal;

uniform mat4 projection;
uniform mat4 world;
uniform mat4 view;

out vec2 out_uv;

void main(void){
    gl_Position = projection * view * world * vec4(position, 1.0);

    out_uv = uv;
}
