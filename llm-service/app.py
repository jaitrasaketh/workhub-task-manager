from flask import Flask, request, jsonify
import requests
from prompt_utils import format_task_prompt

app = Flask(__name__)

OLLAMA_URL = "http://localhost:11434/api/generate"
MODEL_NAME = "llama3"  

@app.route('/generate', methods=['POST'])
def generate():
    data = request.get_json()
    title = data.get('title')
    description = data.get('description')

    if not title or not description:
        return jsonify({'error': 'Both title and description are required'}), 400

    prompt = format_task_prompt(title, description)

    ollama_payload = {
        "model": MODEL_NAME,
        "prompt": prompt,
        "stream": False
    }

    try:
        response = requests.post(OLLAMA_URL, json=ollama_payload)
        response.raise_for_status()
        result = response.json()
        suggestion = result.get("response", "").strip()

        return jsonify({"suggestion": suggestion})

    except Exception as e:
        return jsonify({"error": str(e)}), 500

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5001)
