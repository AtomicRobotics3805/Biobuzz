{
  "startPoint": {
    "x": 63,
    "y": 133.2,
    "locked": false,
    "headingDeg": 90
  },
  "lines": [
    {
      "id": "line-mv8ps4jn-quhav6",
      "color": "#ffc516",
      "name": "Path 1",
      "locked": false,
      "waitBeforeMs": 0,
      "waitAfterMs": 0,
      "waitBeforeName": "",
      "waitAfterName": "",
      "kind": "atomic",
      "endPoint": {
        "x": 11,
        "y": 119
      },
      "controlPoints": [
        {
          "x": 46.4,
          "y": 110.6
        }
      ],
      "heading": {
        "type": "piecewise",
        "startDeg": 270,
        "endDeg": 270,
        "piecewiseHeading": {
          "segments": [
            {
              "startProgress": 0,
              "endProgress": 0.0863,
              "interpolationType": "constant",
              "reversed": false,
              "continueFromPrevious": false,
              "parameters": {
                "degrees": 270
              }
            },
            {
              "startProgress": 0.0863,
              "endProgress": 1,
              "interpolationType": "linear",
              "reversed": false,
              "continueFromPrevious": true,
              "parameters": {
                "startDeg": 0,
                "endDeg": 0
              }
            }
          ]
        },
        "degrees": 180
      }
    }
  ],
  "shapes": [
    {
      "id": "triangle-1",
      "name": "Opposing Side",
      "vertices": [
        {
          "x": 141.5,
          "y": 0
        },
        {
          "x": 141.5,
          "y": 141.5
        },
        {
          "x": 70.75,
          "y": 141.5
        },
        {
          "x": 70.75,
          "y": 0
        }
      ],
      "color": "#dc2626",
      "fillColor": "#ff6b6b"
    },
    {
      "id": "triangle-2",
      "name": "Hive Leg",
      "vertices": [
        {
          "x": 51,
          "y": 48
        },
        {
          "x": 51,
          "y": 93
        },
        {
          "x": 45,
          "y": 93
        },
        {
          "x": 45,
          "y": 48
        }
      ],
      "color": "#2563eb",
      "fillColor": "#60a5fa"
    }
  ],
  "sequence": [
    {
      "kind": "path",
      "lineId": "line-mv8ps4jn-quhav6"
    }
  ],
  "fieldPoints": [],
  "version": "1.5.0",
  "timestamp": "2026-10-17T05:43:20.409Z"
}