#version 410

out vec4 final_color;

in vec2 out_uv;

uniform vec4 color;
uniform sampler2D tex;

void main(void){
    final_color = texture(tex, out_uv) * color;
}

