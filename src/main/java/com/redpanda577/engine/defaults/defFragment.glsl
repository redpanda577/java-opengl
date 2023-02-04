#version 410

out vec4 final_color;

in vec2 out_uv;

uniform vec4 color;
uniform bool useTex;
uniform sampler2D tex;

void main(void){
    if(useTex == true){
        final_color = texture(tex, out_uv) * color;
    }
    else{
        final_color = color;
    }
}

